package com.example.myapplication.ui.view

// app/src/main/java/com/example/practice7/ui/view/AddNoteDialog.kt


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.data.local.entity.NoteEntity
import com.example.myapplication.databinding.DialogAddNoteBinding

class AddNoteDialog(
    private val context: Context,
    private val note: NoteEntity? = null,
    private val onSave: (title: String, content: String) -> Unit
) {
    fun show() {
        val binding = DialogAddNoteBinding.inflate(LayoutInflater.from(context))

        note?.let {
            binding.etTitle.setText(it.title)
            binding.etContent.setText(it.content)
        }

        AlertDialog.Builder(context)
            .setTitle(if (note == null) "Добавить заметку" else "Редактировать заметку")
            .setView(binding.root)
            .setPositiveButton("Сохранить") { _, _ ->
                val title = binding.etTitle.text.toString().trim()
                val content = binding.etContent.text.toString().trim()
                if (title.isNotEmpty()) {
                    onSave(title, content)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}