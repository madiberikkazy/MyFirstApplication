package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.viewmodel.MainViewModel
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountInput = findViewById<EditText>(R.id.amountInput)
        val discountInput = findViewById<EditText>(R.id.discountInput)
        val calcButton = findViewById<Button>(R.id.calcButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val errorText = findViewById<TextView>(R.id.errorText)

        // Наблюдаем за состоянием
        viewModel.state.observe(this) { state ->
            resultText.text = state.resultText
            errorText.text = state.errorText
        }

        // Кнопка
        calcButton.setOnClickListener {
            viewModel.calculate(
                amountInput.text.toString(),
                discountInput.text.toString()
            )
        }
    }
}