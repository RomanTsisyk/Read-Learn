package com.roman_tsisyk.readandlearn.ui.deitydetail


import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman_tsisyk.readandlearn.DeityRepository
import com.roman_tsisyk.readandlearn.data.model.DeityResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeityDetailViewModel @Inject constructor(
    private val repository: DeityRepository
) : ViewModel() {

    private val _deity = MutableStateFlow<DeityResponse?>(null)
    val deity: StateFlow<DeityResponse?> get() = _deity

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun fetchDeity(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDeity(id)
                _deity.value = response
            } catch (e: HttpException) {
                // Handle HTTP errors
                println("HTTP error: ${e.message}")
                e.printStackTrace()
            } catch (e: Exception) {
                // Handle other errors
                println("Error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}

