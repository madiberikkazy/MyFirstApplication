package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.UiState

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> = _state

    fun calculate(amountStr: String, discountStr: String) {

        val amount = amountStr.toDoubleOrNull()
        val discount = discountStr.toDoubleOrNull()

        // Валидация
        if (amount == null || discount == null) {
            _state.value = UiState(
                errorText = "Введите корректные числа",
                isValid = false
            )
            return
        }

        if (amount <= 0 || discount < 0 || discount > 100) {
            _state.value = UiState(
                errorText = "Некорректные значения",
                isValid = false
            )
            return
        }

        val result = amount - (amount * discount / 100)

        _state.value = UiState(
            resultText = "Итоговая сумма: $result",
            errorText = "",
            isValid = true
        )
    }
}