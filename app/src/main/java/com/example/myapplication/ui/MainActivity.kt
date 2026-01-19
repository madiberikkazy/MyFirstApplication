package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.viewmodel.MainViewModel
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val editText = findViewById<EditText>(R.id.editTextInput)
        val button = findViewById<Button>(R.id.btnShow)
        val textView = findViewById<TextView>(R.id.textResult)

        button.setOnClickListener {
            viewModel.setText(editText.text.toString())
        }

        viewModel.text.observe(this) {
            textView.text = it
        }
    }
}