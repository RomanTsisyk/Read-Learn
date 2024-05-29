package slavic_api.github.io.android.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import slavic_api.github.io.android.data.model.SettingOption
import slavic_api.github.io.android.ui.theme.ThemeViewModel

@Composable
fun SettingsScreen() {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkTheme by themeViewModel.isDarkTheme

    val settingsOptions = listOf(
        SettingOption("Toggle Dark Theme") {
            themeViewModel.toggleTheme()
        },
        SettingOption("Adjust Text Size") {
            // Placeholder action
        },
        SettingOption("Account Settings") {
            // Navigate to Account Settings Screen or show dialog
        },
        SettingOption("Privacy Settings") {
            // Navigate to Privacy Settings Screen or show dialog
        },
        SettingOption("Notification Settings") {
            // Navigate to Notification Settings Screen or show dialog
        },
        SettingOption("Language Selection") {
            // Navigate to Language Selection Screen or show dialog
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.titleMedium)

        if (isDarkTheme) {
            MaterialTheme(colorScheme = darkColorScheme()) {
                SettingsScreenContent(settingsOptions, themeViewModel)
            }
        } else {
            MaterialTheme(colorScheme = lightColorScheme()) {
                SettingsScreenContent(settingsOptions, themeViewModel)
            }
        }
    }
}

@Composable
fun SettingItem(settingOption: SettingOption) {
    Text(
        text = settingOption.title,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { settingOption.action() }
    )
}

@Composable
fun SettingsScreenContent(settingsOptions: List<SettingOption>, themeViewModel: ThemeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
//        settingsOptions.forEach { settingOption ->
//            SettingItem(settingOption)
//            Divider()
//        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Theme Settings", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { themeViewModel.toggleTheme() }) {
            Text("Toggle Theme")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Text Size: ${(themeViewModel.textSizeFactor.value * 16).toInt()}sp", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { themeViewModel.increaseTextSize() }) {
            Text("Increase Text Size")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { themeViewModel.decreaseTextSize() }) {
            Text("Decrease Text Size")
        }
    }
}
