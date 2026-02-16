package com.example.myapplication.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.model.Task

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        _tasks.value = List(10) { index ->
            Task(
                id = index + 1,
                title = "Task ${index + 1}",
                description = "Description for task ${index + 1}"
            )
        }
    }
}