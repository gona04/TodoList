package com.example.todolist

import AddNewTaskFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityMainBinding
import ToDoModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.ToDoAdapter

import com.example.todolist.viewmodel.ToDoViewModel


/*
    MainActivity implements Two interfaces
    1. AppCompatActivity: (Default interface for the Activity class)
    2. ToDoAdapter.OnInteractionInterface used for Deleting an item in
    recycler view
 */
class MainActivity : AppCompatActivity(), ToDoAdapter.OnInteractionInterface {
    //This is for ViewBinding with activity_main.xml
    private lateinit var viewBinding: ActivityMainBinding
    //Instance of todoAdapter for recycler view
    private lateinit var taskAdapter: ToDoAdapter
    //List of Todo Tasks
    private  var taskList: MutableList<ToDoModel> = mutableListOf()
    /*
    * Since this application uses fragments we need an
    * a way to get the latest change made on a variable
    * from various other fragments.
    * ViewModels are used to get the updates of
    * the toDolist.
    * */
    private lateinit var toDoviewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.hide()

        toDoviewModel = ViewModelProvider(this)[ToDoViewModel::class.java]

        //Creating the recycler view dynamically
          viewBinding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
          taskAdapter = ToDoAdapter(this)
          viewBinding.tasksRecyclerView.adapter = taskAdapter
          taskList = mutableListOf() // Initialize the taskList with an empty MutableList

        addBtnClicked()
        observeTodoLiveData()
    }


    /*
    * Everytime the value of todoModel is changed this method is called
    * to get the latest value of the model object
    * */
    private fun observeTodoLiveData() {

        toDoviewModel.todoMutableLiveData.observe(this) { updatedTodo ->
            // Handle updatedTodo, which contains the latest data
            taskList.add(updatedTodo)
            taskAdapter.setTodoList(taskList)
        }
    }

    /*
    * This method instantiates the BottomSheetDialogFragment on click listener
    * of the the add button
    * */
    fun addBtnClicked() {
        viewBinding.fab.setOnClickListener {
            val addNewTaskFragmentDialog = AddNewTaskFragment.newInstance(toDoViewModel = toDoviewModel)
            addNewTaskFragmentDialog.show(supportFragmentManager, AddNewTaskFragment.TAG)
        }
    }

    /*
    * When the delete button is clicked this method is called
    * it is a method from the adapter class. As the item to be
    * removed is passed from the adapter class
    * */
    override fun onDelete(item: ToDoModel) {
        taskList.remove(item)
        taskAdapter.setTodoList(taskList)
    }
}

