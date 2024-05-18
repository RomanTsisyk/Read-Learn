package com.roman_tsisyk.readandlearn.ui.views

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.roman_tsisyk.readandlearn.MyApp
import com.roman_tsisyk.readandlearn.ui.MainScreen
import com.roman_tsisyk.readandlearn.ui.theme.ReadAndLeardTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}