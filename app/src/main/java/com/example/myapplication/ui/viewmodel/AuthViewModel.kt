package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun register(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email и пароль не могут быть пустыми")
            return
        }
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _authState.value = AuthState.Success(auth.currentUser)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Ошибка регистрации")
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email и пароль не могут быть пустыми")
            return
        }
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _authState.value = AuthState.Success(auth.currentUser)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Ошибка входа")
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.LoggedOut
    }

    sealed class AuthState {
        object Loading : AuthState()
        object LoggedOut : AuthState()
        data class Success(val user: FirebaseUser?) : AuthState()
        data class Error(val message: String) : AuthState()
    }
}