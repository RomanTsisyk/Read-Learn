package com.roman_tsisyk.readandlearn.data.api

import com.roman_tsisyk.readandlearn.data.model.DeitiesResponse
import com.roman_tsisyk.readandlearn.data.model.DeityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SlavicApiService {

    @GET("slavic-api/API/main/en/deities_list.json")
    suspend fun fetchDeities(): Response<DeitiesResponse>

    @GET("slavic-api/API/main/en/deities/{id}.json")
    suspend fun getDeity(@Path("id") id: String): DeityResponse

}