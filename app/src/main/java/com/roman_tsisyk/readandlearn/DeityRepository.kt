package com.roman_tsisyk.readandlearn

import com.roman_tsisyk.readandlearn.data.api.SlavicApiService

class DeityRepository(private val apiService: SlavicApiService) {
    suspend fun getDeities() = apiService.fetchDeities()
}