package slavic_api.github.io.android

import slavic_api.github.io.android.data.api.SlavicApiService
import slavic_api.github.io.android.data.database.DeityDao
import slavic_api.github.io.android.data.model.Deity
import slavic_api.github.io.android.data.model.DeityResponse

class DeityRepository(private val apiService: SlavicApiService, private val deityDao: DeityDao) {

    suspend fun getDeities(): Result<List<Deity>> {
        return try {
            val response = apiService.fetchDeities()
            if (response.isSuccessful) {
                response.body()?.let { deityResponse ->
                    deityDao.insertDeities(deityResponse.gods)
                    Result.success(deityResponse.gods)
                } ?: Result.failure(Exception("No data available"))
            } else {
                Result.failure(Exception("Network call failed"))
            }
        } catch (e: Exception) {
            val cachedDeities = deityDao.getAllDeities()
            if (cachedDeities.isNotEmpty()) {
                Result.success(cachedDeities)
            } else {
                Result.failure(Exception(e.message ?: "Unknown error"))
            }
        }
    }

    suspend fun getDeity(id: String): Result<DeityResponse> {
        return try {
            val response = apiService.getDeity(id)
            if (response.toString().isNotEmpty()) {
                response.let { deity ->
                    deityDao.insertDeity(deity)
                    Result.success(deity)
                }
            } else {
                Result.failure(Exception("Network call failed"))
            }
        } catch (e: Exception) {
            val cachedDeity = deityDao.getDeityById(id)
            cachedDeity?.let {
                Result.success(cachedDeity)
            } ?: Result.failure(Exception(e.message ?: "Unknown error"))
        }
    }
}
