package com.example.myapplication.data.repository

// app/src/main/java/com/example/practice7/data/repository/NoteRepository.kt

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.dao.NoteDao
import com.example.myapplication.data.local.entity.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insert(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    suspend fun delete(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteById(noteId: Int) {
        noteDao.deleteNoteById(noteId)
    }
}