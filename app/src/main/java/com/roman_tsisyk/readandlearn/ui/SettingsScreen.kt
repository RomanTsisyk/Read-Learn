package com.roman_tsisyk.readandlearn.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.roman_tsisyk.readandlearn.data.model.SettingOption

@Composable
fun SettingsScreen() {
    val isDarkTheme = remember { mutableStateOf(false) }

    val settingsOptions = listOf(
        SettingOption("Toggle Dark Theme") {
            isDarkTheme.value = !isDarkTheme.value
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
        Spacer(modifier = Modifier.height(16.dp))
        settingsOptions.forEach { settingOption ->
            SettingItem(settingOption)
            Divider()
        }
    }

    if (isDarkTheme.value) {
        MaterialTheme(colorScheme = darkColorScheme()) {
            SettingsScreenContent(settingsOptions)
        }
    } else {
        MaterialTheme(colorScheme = lightColorScheme()) {
            SettingsScreenContent(settingsOptions)
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
fun SettingsScreenContent(settingsOptions: List<SettingOption>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Settings", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        settingsOptions.forEach { settingOption ->
            SettingItem(settingOption)
            Divider()
        }
    }
}
