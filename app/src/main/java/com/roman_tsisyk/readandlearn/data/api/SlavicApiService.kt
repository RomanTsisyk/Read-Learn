package com.roman_tsisyk.readandlearn.data.api

import com.roman_tsisyk.readandlearn.data.model.DeitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface SlavicApiService {

    @GET("slavic-api/API/main/en/deities_list.json")
    suspend fun fetchDeities(): Response<DeitiesResponse>
}