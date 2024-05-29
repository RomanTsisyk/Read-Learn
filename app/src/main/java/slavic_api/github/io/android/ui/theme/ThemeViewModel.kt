package slavic_api.github.io.android.ui.theme

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ThemeViewModel : ViewModel() {
    val isDarkTheme = mutableStateOf(false)
    val textSizeFactor = mutableStateOf(1.0f)

    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }

    fun increaseTextSize() {
        textSizeFactor.value += 0.1f
    }

    fun decreaseTextSize() {
        if (textSizeFactor.value > 0.5f) {
            textSizeFactor.value -= 0.1f
        }
    }
}