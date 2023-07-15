package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Adapter.ToDoAdapter
import com.example.todolist.Model.ToDoModel
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var taskAdapter: ToDoAdapter

    private lateinit var taskList: MutableList<ToDoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        viewBinding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = ToDoAdapter(this)
        viewBinding.tasksRecyclerView.adapter = taskAdapter

        taskList = mutableListOf() // Initialize the taskList with an empty MutableList

        var task = ToDoModel()
        task.setId(1)
        task.setTask("Testing ${task.getId()}")
        task.setStatus(false)

        taskList.add(task)
        taskList.add(task)
        taskList.add(task)
        taskList.add(task)
        taskList.add(task)

        taskAdapter.setTasks(taskList)
    }
}

