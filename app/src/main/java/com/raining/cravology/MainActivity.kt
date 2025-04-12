@file:OptIn(ExperimentalMaterial3Api::class)

package com.raining.cravology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // I'm going to use the Jetpack Compose feature
        setContent {
            CravologyApp() // main composable
        }
    }
}

@Composable
fun CravologyApp() {
    // Surface will be the container for everything
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = {
                // Added a centered top app bar with the app name
                CenterAlignedTopAppBar(
                    title = { Text("Cravology") }
                )
            }
        ) { padding ->
            // thiss will hold all the UI elements in the center
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Placeholder – the buttons will go here in the next commit
            }
        }
    }
}
