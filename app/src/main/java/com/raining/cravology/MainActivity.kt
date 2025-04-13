package com.raining.cravology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
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

//so the icon mathches
@Composable
fun getIconForOption(option: String): Int {
    return when (option) {
        "Salty" -> R.drawable.salty
        "Sweet" -> R.drawable.sweet
        "Spicy" -> R.drawable.spicy
        "Sour" -> R.drawable.sour
        "Umami" -> R.drawable.umami
        "Crunchy" -> R.drawable.crunchy
        "Soft" -> R.drawable.soft
        "Chewy" -> R.drawable.chewy
        "Crispy" -> R.drawable.crispy
        "Hot" -> R.drawable.hot
        "Cold" -> R.drawable.cold
        "Bitter" -> R.drawable.bitter
        else -> R.drawable.salty // if no matchs
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CravologyApp() {
    // Surface will be the container for everything
    Surface(color = MaterialTheme.colorScheme.background) {

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
        var result by remember { mutableStateOf("") }
        var showDialog by remember { mutableStateOf(false) }

        //    this took a while
        val foodCombos = listOf(
            // Sweet combinations
            FoodCombo(setOf("Sweet", "Crunchy", "Hot"), "Chocolate Bar"),
            FoodCombo(setOf("Sweet", "Crunchy", "Cold"), "Ice Cream"),
            FoodCombo(setOf("Sweet", "Crunchy", "Bitter"), "Candy Bar"),
            FoodCombo(setOf("Sweet", "Chewy", "Hot"), "Donut"),
            FoodCombo(setOf("Sweet", "Chewy", "Cold"), "Fruit Salad"),
            FoodCombo(setOf("Sweet", "Chewy", "Bitter"), "Ginger Candy"),
            FoodCombo(setOf("Sweet", "Soft", "Hot"), "Apple Pie"),
            FoodCombo(setOf("Sweet", "Soft", "Cold"), "Frozen Yogurt"),
            FoodCombo(setOf("Sweet", "Soft", "Bitter"), "Coffee Chocolate Cake"),
            FoodCombo(setOf("Sweet", "Crispy", "Hot"), "Sweet Potato Fries"),
            FoodCombo(setOf("Sweet", "Crispy", "Cold"), "Churios"),
            FoodCombo(setOf("Sweet", "Crispy", "Bitter"), "Dark Chocolate-Covered Pretzels"),

            // Salty combinations
            FoodCombo(setOf("Salty", "Crunchy", "Hot"), "Pretzels"),
            FoodCombo(setOf("Salty", "Crunchy", "Cold"), "Salted Cucumbers"),
            FoodCombo(setOf("Salty", "Crunchy", "Bitter"), "Salted Olives"),
            FoodCombo(setOf("Salty", "Chewy", "Hot"), "Soft Pretzels"),
            FoodCombo(setOf("Salty", "Chewy", "Cold"), "Salted Caramel Chews"),
            FoodCombo(setOf("Salty", "Chewy", "Bitter"), "Pickles"),
            FoodCombo(setOf("Salty", "Soft", "Hot"), "Pizza"),
            FoodCombo(setOf("Salty", "Soft", "Cold"), "Ham Sandwich"),
            FoodCombo(setOf("Salty", "Soft", "Bitter"), "Bread Rolls"),
            FoodCombo(setOf("Salty", "Crispy", "Hot"), "Fries"),
            FoodCombo(setOf("Salty", "Crispy", "Cold"), "Tacos"),
            FoodCombo(setOf("Salty", "Crispy", "Bitter"), "Nachos"),

            // Spicy combinations
            FoodCombo(setOf("Spicy", "Crunchy", "Hot"), "Spicy Nachos"),
            FoodCombo(setOf("Spicy", "Crunchy", "Cold"), "Spicy Chips"),
            FoodCombo(setOf("Spicy", "Crunchy", "Bitter"), "Spicy Arugula Salad"),
            FoodCombo(setOf("Spicy", "Chewy", "Hot"), "Spicy Wings"),
            FoodCombo(setOf("Spicy", "Chewy", "Cold"), "Spicy Salsa"),
            FoodCombo(setOf("Spicy", "Chewy", "Bitter"), "Spicy Burrito"),
            FoodCombo(setOf("Spicy", "Soft", "Hot"), "Hot Chili"),
            FoodCombo(setOf("Spicy", "Soft", "Cold"), "Spicy Soup"),
            FoodCombo(setOf("Spicy", "Soft", "Bitter"), "Spicy Tofu"),
            FoodCombo(setOf("Spicy", "Crispy", "Hot"), "Spicy Fried Chicken"),
            FoodCombo(setOf("Spicy", "Crispy", "Cold"), "Spicy Tempura"),
            FoodCombo(setOf("Spicy", "Crispy", "Bitter"), "Spicy Pizza"),

            // Sour combinations
            FoodCombo(setOf("Sour", "Crunchy", "Hot"), "Sour Tortilla Chips"),
            FoodCombo(setOf("Sour", "Crunchy", "Cold"), "Sour Chips"),
            FoodCombo(setOf("Sour", "Crunchy", "Bitter"), "Sour Crackers"),
            FoodCombo(setOf("Sour", "Chewy", "Hot"), "Sour Gummy Bears"),
            FoodCombo(setOf("Sour", "Chewy", "Cold"), "Sour Yogurt"),
            FoodCombo(setOf("Sour", "Chewy", "Bitter"), "Sour Candy"),
            FoodCombo(setOf("Sour", "Soft", "Hot"), "Sour Pie"),
            FoodCombo(setOf("Sour", "Soft", "Cold"), "Sour Ice Cream"),
            FoodCombo(setOf("Sour", "Soft", "Bitter"), "Sour Cake"),
            FoodCombo(setOf("Sour", "Crispy", "Hot"), "Sour Chips"),
            FoodCombo(setOf("Sour", "Crispy", "Cold"), "Sour Fries"),
            FoodCombo(setOf("Sour", "Crispy", "Bitter"), "Sour Crackers"),

            // Umami combinations
            FoodCombo(setOf("Umami", "Crunchy", "Hot"), "Umami Fries"),
            FoodCombo(setOf("Umami", "Crunchy", "Cold"), "Umami Chips"),
            FoodCombo(setOf("Umami", "Crunchy", "Bitter"), "Umami Popcorn"),
            FoodCombo(setOf("Umami", "Chewy", "Hot"), "Umami Burgers"),
            FoodCombo(setOf("Umami", "Chewy", "Cold"), "Umami Wrap"),
            FoodCombo(setOf("Umami", "Chewy", "Bitter"), "Umami Sandwich"),
            FoodCombo(setOf("Umami", "Soft", "Hot"), "Umami Rice"),
            FoodCombo(setOf("Umami", "Soft", "Cold"), "Cold Sushi"),
            FoodCombo(setOf("Umami", "Soft", "Bitter"), "Umami Soup"),
            FoodCombo(setOf("Umami", "Crispy", "Hot"), "Crispy Tempeh"),
            FoodCombo(setOf("Umami", "Crispy", "Cold"), "Tempura"),
            FoodCombo(setOf("Umami", "Crispy", "Bitter"), "Fried Tofu")
        )

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("How it works") },
                text = { Text("Pick up to 3 cravings. We'll find a snack that matches your vibe!") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Got it!")
                    }
                }
            )
        }

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
                //pop up instructions
                ClickableText(
                    text = AnnotatedString("Need help? Tap here."),
                    onClick = { showDialog = true },
                    style = LocalTextStyle.current.copy(fontSize = 14.sp)
                )

                // middle text
                Text("Choose up to 3 cravings:", fontSize = 18.sp)


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
                                        //made a column for the icon and text
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                painter = painterResource(id = getIconForOption(option)),
                                                contentDescription = option,
                                                modifier = Modifier.size(44.dp)
                                            )
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

                // resu;t button
                Button(
                    onClick = {
                        val match = foodCombos.find { it.tags == selectedOptions }
                        result = match?.snack ?: "What are you craving? Try a different combo!"
                    },
                    enabled = selectedOptions.size == 3
                ) {
                    Text("What do I Crave!?")
                }
                Text(
                    text = result,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// the class of combos for the flalvors
data class FoodCombo(val tags: Set<String>, val snack: String)
