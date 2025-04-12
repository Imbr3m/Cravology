@file:OptIn(ExperimentalMaterial3Api::class)

package com.raining.cravology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // I'm going to use the Jetpack Compose feature
        setContent {
            CravologyApp() // main composable
        }
    }
}

@Preview(showBackground = true)
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
                // middle text
                Text("Choose up to 3 cravings:", fontSize = 18.sp)

                // flavor lists
                val allOptions = listOf(
                    "Salty", "Sweet", "Spicy", "Sour", "Umami",
                    "Crunchy", "Soft", "Chewy", "Crispy",
                    "Hot", "Cold", "Bitter"
                )

                // tracking the flavors
                var selectedOptions by remember { mutableStateOf(setOf<String>()) }

                // limits to only 3 flavors
                val chunkedOptions = allOptions.chunked(3)

                // column
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    chunkedOptions.forEach { rowItems ->
                        // each row has threee buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            rowItems.forEach { option ->
                                val isSelected = selectedOptions.contains(option)

                                // flavor option button
                                AssistChip(
                                    onClick = {
                                        selectedOptions = if (isSelected) {
                                            selectedOptions - option // unselect
                                        } else if (selectedOptions.size < 3) {
                                            selectedOptions + option // adds 1
                                        } else {
                                            selectedOptions // do nothing if 3 selected already
                                        }
                                    },
                                    label = {
                                        // to center the text i made a div thingie
                                        Box(
                                            modifier = Modifier.fillMaxWidth(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(option)
                                        }
                                    },
                                    modifier = Modifier
                                        .size(100.dp)
                                        .border(0.dp, Color.Gray, RoundedCornerShape(8.dp)), //border radius
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = if (isSelected) Color(0xFFBBDEFB) else Color.LightGray //change color when clicked
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
