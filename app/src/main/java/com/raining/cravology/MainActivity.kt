package com.raining.cravology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // im going to use the jetpack feature
        setContent {
            CravologyApp() // main composable
        }
    }
}

@Composable
fun CravologyApp() {
    // will be the container for the buttons
    Surface(color = MaterialTheme.colorScheme.background) {
    }
}
