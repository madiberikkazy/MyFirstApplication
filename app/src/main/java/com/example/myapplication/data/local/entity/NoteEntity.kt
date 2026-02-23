package com.example.myapplication.data.local.entity

// app/src/main/java/com/example/practice7/data/local/entity/NoteEntity.kt

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)