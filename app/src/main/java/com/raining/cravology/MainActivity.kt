package com.raining.cravology

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val selectedOptions = mutableSetOf<String>()
    private lateinit var craveButton: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        craveButton = findViewById(R.id.craveButton)
        resultText = findViewById(R.id.resultText)
        val helpText = findViewById<TextView>(R.id.helpText)
        val optionsGrid = findViewById<GridLayout>(R.id.optionsGrid)

        // Set up grid with food options
        setupFoodOptions(optionsGrid)

        // Help dialog setup
        helpText.setOnClickListener {
            showHelpDialog()
        }

        // Crave button setup
        craveButton.setOnClickListener {
            findCrave()
        }
    }

    private fun setupFoodOptions(grid: GridLayout) {
        val allOptions = listOf(
            "Salty", "Sweet", "Spicy", "Sour", "Umami",
            "Crunchy", "Soft", "Chewy", "Crispy",
            "Hot", "Cold", "Bitter"
        )

        val inflater = LayoutInflater.from(this)

        for (i in allOptions.indices) {
            val option = allOptions[i]

            val itemView = inflater.inflate(R.layout.food_item, grid, false) as CardView
            val foodIcon = itemView.findViewById<ImageView>(R.id.foodIcon)
            val foodName = itemView.findViewById<TextView>(R.id.foodName)

            // Set icon and name
            foodIcon.setImageResource(getIconForOption(option))
            foodName.text = option

            // Handle selection
            itemView.setOnClickListener {
                toggleSelection(option, itemView)
            }

            // Add to grid
            val row = i / 3
            val col = i % 3
            val param = GridLayout.LayoutParams()
            param.rowSpec = GridLayout.spec(row)
            param.columnSpec = GridLayout.spec(col)
            grid.addView(itemView, param)
        }
    }

    private fun toggleSelection(option: String, cardView: CardView) {
        if (selectedOptions.contains(option)) {
            // Deselect
            selectedOptions.remove(option)
            // Use the ContextCompat version for backward compatibility
            cardView.setCardBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
        } else if (selectedOptions.size < 3) {
            // Select if under 3 selections
            selectedOptions.add(option)
            cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selected_color))
        }

        // Update button state
        craveButton.isEnabled = selectedOptions.size == 3
    }


    private fun getIconForOption(option: String): Int {
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
            else -> R.drawable.salty
        }
    }

    private fun showHelpDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_help, null)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Got it!") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun findCrave() {
        val foodCombos = listOf(
            FoodCombo(setOf("Sweet", "Crunchy", "Hot"), "Chocolate Bar"),
            FoodCombo(setOf("Sweet", "Crunchy", "Cold"), "Ice Cream"),
            // ... Copy all the food combinations from your original code ...
            FoodCombo(setOf("Umami", "Crispy", "Bitter"), "Fried Tofu")
        )

        val match = foodCombos.find { it.tags == selectedOptions }
        val result = match?.snack ?: "What are you craving? Try a different combo!"

        resultText.text = result
    }

    data class FoodCombo(val tags: Set<String>, val snack: String)
}