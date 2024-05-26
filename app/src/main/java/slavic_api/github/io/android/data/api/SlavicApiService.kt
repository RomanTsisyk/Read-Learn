package slavic_api.github.io.android.data.api

import slavic_api.github.io.android.data.model.DeitiesResponse
import slavic_api.github.io.android.data.model.DeityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SlavicApiService {

    @GET("slavic-api/API/main/en/deities_list.json")
    suspend fun getDeities(): Response<DeitiesResponse>

    @GET("slavic-api/API/main/en/deities/{id}.json")
    suspend fun getDeity(@Path("id") id: String): DeityResponse

}