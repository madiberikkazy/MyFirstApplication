package com.example.myapplication.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.local.entity.NoteEntity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.adapter.NoteAdapter
import com.example.myapplication.ui.viewmodel.AuthViewModel
import com.example.myapplication.ui.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val noteViewModel: NoteViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Guard: if not logged in, redirect to LoginActivity
        if (authViewModel.currentUser == null) {
            navigateToLogin()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Show logged-in user email in subtitle
        supportActionBar?.subtitle = authViewModel.currentUser?.email

        setupRecyclerView()
        observeNotes()
        observeAuthState()

        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                authViewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        adapter = NoteAdapter(
            onDelete = { note -> noteViewModel.deleteNote(note) },
            onEdit = { note -> showEditDialog(note) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeNotes() {
        noteViewModel.allNotes.observe(this) { notes ->
            adapter.submitList(notes)
        }
    }

    private fun observeAuthState() {
        authViewModel.authState.observe(this) { state ->
            if (state is AuthViewModel.AuthState.LoggedOut) {
                navigateToLogin()
            }
        }
    }

    private fun showAddDialog() {
        AddNoteDialog(
            context = this,
            onSave = { title, content ->
                noteViewModel.addNote(title, content)
            }
        ).show()
    }

    private fun showEditDialog(note: NoteEntity) {
        AddNoteDialog(
            context = this,
            note = note,
            onSave = { title, content ->
                noteViewModel.updateNote(note.copy(title = title, content = content))
            }
        ).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}