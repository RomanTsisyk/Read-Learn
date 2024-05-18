package com.roman_tsisyk.readandlearn

import com.roman_tsisyk.readandlearn.data.api.SlavicApiService
import com.roman_tsisyk.readandlearn.data.model.DeityResponse

class DeityRepository(private val apiService: SlavicApiService) {
    suspend fun getDeities() = apiService.fetchDeities()

    suspend fun getDeity(id: String): DeityResponse = apiService.getDeity(id)

}