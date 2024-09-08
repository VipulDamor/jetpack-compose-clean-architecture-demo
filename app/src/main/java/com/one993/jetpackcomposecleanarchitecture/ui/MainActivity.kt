package com.one993.jetpackcomposecleanarchitecture.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.one993.jetpackcomposecleanarchitecture.core.navigation_utils.AppNavigation
import com.one993.jetpackcomposecleanarchitecture.ui.theme.JetpackComposeCleanArchitectureTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeCleanArchitectureTheme {
                AppNavigation()
            }
        }
    }
}

