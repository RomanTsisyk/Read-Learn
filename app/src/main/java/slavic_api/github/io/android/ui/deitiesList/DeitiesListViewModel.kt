package slavic_api.github.io.android.ui.deitiesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import slavic_api.github.io.android.DeityRepository
import slavic_api.github.io.android.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import slavic_api.github.io.android.data.model.Deity
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
        viewModelScope.launch {
            _deities.value = Result.Loading
            try {
                val result: Result<List<Deity>> = repository.getAllDeities()
                _deities.postValue(result)
            } catch (e: Exception) {
                _deities.postValue(Result.Error(e))
            }
        }
    }
}
