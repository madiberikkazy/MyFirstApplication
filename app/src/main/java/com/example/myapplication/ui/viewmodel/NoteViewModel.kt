package com.example.myapplication.ui.viewmodel

import com.example.myapplication.data.local.entity.NoteEntity
import com.example.myapplication.data.repository.NoteRepository

// app/src/main/java/com/example/practice7/ui/viewmodel/NoteViewModel.kt


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.database.AppDatabase

import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val allNotes: LiveData<List<NoteEntity>>

    init {
        val dao = AppDatabase.getInstance(application).noteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun addNote(title: String, content: String) {
        val note = NoteEntity(title = title, content = content)
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}