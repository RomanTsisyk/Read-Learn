package com.roman_tsisyk.readandlearn.ui.deitiesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman_tsisyk.readandlearn.DeityRepository
import com.roman_tsisyk.readandlearn.data.model.DeitiesResponse
import com.roman_tsisyk.readandlearn.data.model.Deity
import com.roman_tsisyk.readandlearn.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DeitiesListViewModel @Inject constructor(
    private val repository: DeityRepository
) : ViewModel() {

    private val _deities = MutableLiveData<Result<List<Deity>>>()
    val deities: LiveData<Result<List<Deity>>> = _deities

    init {
        fetchDeities()
    }

    private fun fetchDeities() {
        viewModelScope.launch(Dispatchers.IO) {
            _deities.postValue(Result.Loading)
            try {
                val response: Response<DeitiesResponse> = repository.getDeities()
                if (response.isSuccessful && response.body() != null) {
                    _deities.postValue(Result.Success(response.body()?.gods ?: emptyList()))
                } else {
                    _deities.postValue(
                        Result.Error(Exception( "Failed to fetch data" ))
                    )
                }

            } catch (e: Exception) {
                _deities.postValue(Result.Error(e))
            }
        }
    }
}
