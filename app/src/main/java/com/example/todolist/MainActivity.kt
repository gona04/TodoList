package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Adapter.ToDoAdapter
import com.example.todolist.Model.ToDoModel
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var viewBinding : ActivityMainBinding
    private lateinit var taskAdapter: ToDoAdapter

    private lateinit var textList: List<ToDoModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        viewBinding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}