package com.example.myapplication.ui.view

// app/src/main/java/com/example/practice7/ui/view/MainActivity.kt


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.local.entity.NoteEntity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapter.NoteAdapter
import com.example.myapplication.ui.viewmodel.NoteViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeNotes()

        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = NoteAdapter(
            onDelete = { note -> viewModel.deleteNote(note) },
            onEdit = { note -> showEditDialog(note) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeNotes() {
        viewModel.allNotes.observe(this) { notes ->
            adapter.submitList(notes)
        }
    }

    private fun showAddDialog() {
        AddNoteDialog(
            context = this,
            onSave = { title, content ->
                viewModel.addNote(title, content)
            }
        ).show()
    }

    private fun showEditDialog(note: NoteEntity) {
        AddNoteDialog(
            context = this,
            note = note,
            onSave = { title, content ->
                viewModel.updateNote(note.copy(title = title, content = content))
            }
        ).show()
    }
}