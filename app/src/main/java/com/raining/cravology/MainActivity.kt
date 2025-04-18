package com.raining.cravology

//imports
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val selectedOptions = mutableSetOf<String>() //store 3 flavvors

    private lateinit var craveButton: Button
    private lateinit var resultText: TextView

    // for the uhh cardviews
    private val optionViews = mutableMapOf<String, CardView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // main view
        craveButton = findViewById(R.id.craveButton)
        resultText = findViewById(R.id.resultText)
        val helpText = findViewById<TextView>(R.id.helpText)
        val optionsContainer = findViewById<LinearLayout>(R.id.optionsContainer)

        // button container
        setupFoodOptions(optionsContainer)

        // help instructions
        helpText.setOnClickListener {
            showHelpDialog()
        }

        // crave button listiner
        craveButton.setOnClickListener {
            findCrave()
        }
    }

    // flavor button layout
    private fun setupFoodOptions(container: LinearLayout) {
        // list of flavors
        val allOptions = listOf(
            "Salty", "Sweet", "Spicy", "Sour", "Umami",
            "Crunchy", "Soft", "Chewy", "Crispy",
            "Hot", "Cold", "Bitter"
        )

        val inflater = LayoutInflater.from(this)

        // 4 rows of 3 buttons
        for (i in 0 until 4) {
            val rowLayout = LinearLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
                orientation = LinearLayout.HORIZONTAL
                weightSum = 3f
            }

            // 3 nbutton per row
            for (j in 0 until 3) {
                val index = i * 3 + j
                if (index < allOptions.size) {
                    val option = allOptions[index]

                    val itemView = inflater.inflate(R.layout.food_item, rowLayout, false) as CardView
                    val params = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        weight = 1f
                        setMargins(8, 8, 8, 8)
                    }
                    itemView.layoutParams = params

                    // flavor icon and text
                    val foodIcon = itemView.findViewById<android.widget.ImageView>(R.id.foodIcon)
                    val foodName = itemView.findViewById<TextView>(R.id.foodName)

                    foodIcon.setImageResource(getIconForOption(option))
                    foodName.text = option

                    optionViews[option] = itemView

                    // listnets for when the flavor button clicked
                    itemView.setOnClickListener {
                        toggleSelection(option)
                    }

                    rowLayout.addView(itemView)
                }
            }

            container.addView(rowLayout)
        }
    }

    // function for selecting and deselecting
    private fun toggleSelection(option: String) {
        val cardView = optionViews[option] ?: return

        if (selectedOptions.contains(option)) {
            selectedOptions.remove(option)
            cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cream_color))
        } else if (selectedOptions.size < 3) {
            // limits to only 3
            selectedOptions.add(option)
            cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selected_color))
        }

        // crave button eneables when 3 flavors are chosen
        craveButton.isEnabled = selectedOptions.size == 3
    }

    // finds the right image for the flavor
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

    // instruction pop up
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

    // result function
    private fun findCrave() {
        // list of combos
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

        // matching it if there are a match
        val match = foodCombos.find { it.tags == selectedOptions }

        // if no match
        val result = match?.snack ?: "What are you craving? Try a different combo!"
        resultText.text = result
    }

    // storage of 3 flavors
    data class FoodCombo(val tags: Set<String>, val snack: String)
}
