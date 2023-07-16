package com.example.todolist

import AddNewTaskFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityMainBinding
import ToDoModel
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.ToDoAdapter

import com.example.todolist.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var taskAdapter: ToDoAdapter

    private  var taskList: MutableList<ToDoModel> = mutableListOf()

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]


          viewBinding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
          taskAdapter = ToDoAdapter()
          viewBinding.tasksRecyclerView.adapter = taskAdapter
          taskList = mutableListOf() // Initialize the taskList with an empty MutableList

        addBtnClicked()
        observeTodoLiveData()
    }

    private fun observeTodoLiveData() {

        myViewModel.todoMutableLiveData.observe(this) { updatedTodo ->
            // Handle updatedTodo, which contains the latest data
            Log.d("FROM MAIN", updatedTodo.task)
            taskList.add(updatedTodo)
            taskAdapter.setTodoList(taskList)
        }
    }

    fun addBtnClicked() {
        viewBinding.fab.setOnClickListener {
            val addNewTaskFragmentDialog = AddNewTaskFragment.newInstance(myViewModel = myViewModel)
            addNewTaskFragmentDialog.show(supportFragmentManager, AddNewTaskFragment.TAG)
        }
    }
}

