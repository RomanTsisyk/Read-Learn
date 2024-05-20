package slavic_api.github.io.android

import slavic_api.github.io.android.data.api.SlavicApiService
import slavic_api.github.io.android.data.model.DeityResponse

class DeityRepository(private val apiService: SlavicApiService) {
    suspend fun getDeities() = apiService.fetchDeities()

    suspend fun getDeity(id: String): DeityResponse = apiService.getDeity(id)

}