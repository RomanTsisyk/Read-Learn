package slavic_api.github.io.android

import android.util.Log
import kotlinx.coroutines.delay
import slavic_api.github.io.android.data.api.SlavicApiService
import slavic_api.github.io.android.data.database.DeityDao
import slavic_api.github.io.android.data.model.Deity
import slavic_api.github.io.android.data.model.Result
import slavic_api.github.io.android.data.model.toDeity

class DeityRepository(private val apiService: SlavicApiService, private val deityDao: DeityDao) {

    suspend fun getDeity(id: String): Result<Deity> {
        return try {
            retry(3, 2000) {
                val response = apiService.getDeity(id)
                if (response.toString().isNotEmpty()) {
                    val deity = response.toDeity()
                    Log.d("Roman", "response = $response")
                    Log.d("Roman", "deity = $deity")
                    deityDao.insertDeity(deity)
                    Result.Success(deity)
                } else {
                    Result.Error(Exception("Network call failed"))
                }
            }
        } catch (e: Exception) {
            val cachedDeity = deityDao.getDeityById(id)
            cachedDeity?.let {
                Result.Success(it)
            } ?: Result.Error(Exception(e.message ?: "Unknown error"))
        }
    }

    suspend fun getAllDeities(): Result<List<Deity>> {
        return try {
            retry(3, 2000) {
                val response = apiService.getDeities()
                if (response.isSuccessful) {
                    val deities = response.body()?.gods?.map { it.toDeity() } ?: emptyList()
                    deities.forEach { deityDao.insertDeity(it) }
                    Result.Success(deities)
                } else {
                    Result.Error(Exception("Network call failed"))
                }
            }
        } catch (e: Exception) {
            val cachedDeities = deityDao.getAllDeities()
            if (cachedDeities.isNotEmpty()) {
                Result.Success(cachedDeities)
            } else {
                Result.Error(Exception(e.message ?: "Unknown error"))
            }
        }
    }

    private suspend fun <T> retry(
        times: Int,
        delayMillis: Long,
        block: suspend () -> T
    ): T {
        repeat(times - 1) {
            try {
                return block()
            } catch (e: Exception) {
                Log.e("DeityRepository", "Retrying due to error: ${e.message}")
                delay(delayMillis)
            }
        }
        return block() // last attempt
    }
}