package com.example.todolist

import AddNewTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Adapter.ToDoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import ToDoModel
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var taskAdapter: ToDoAdapter

    private  var taskList: MutableList<ToDoModel> = mutableListOf()

    private lateinit var todo: ToDoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        todo = ViewModelProvider(this).get(ToDoModel::class.java)

        observeTodoLiveData()
        /*  viewBinding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
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

          taskAdapter.setTasks(taskList)*/
        addBtnClicked()
    }

    fun observeTodoLiveData() {
        todo.getTodoLiveData().observe(this) { updatedTodo ->
            // Handle updatedTodo, which contains the latest data
            Log.d("FROM MAIN", updatedTodo.getTask())
        }
    }

    fun addBtnClicked() {
        viewBinding.fab.setOnClickListener {
            val addNewTaskDialog = AddNewTask.newInstance(todo)
            addNewTaskDialog.show(supportFragmentManager, AddNewTask.TAG)
        }
    }
}

