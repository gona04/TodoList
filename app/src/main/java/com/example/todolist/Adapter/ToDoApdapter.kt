package com.example.todolist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.MainActivity
import ToDoModel
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding

class ToDoAdapter(private val activity: MainActivity) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    constructor(activity: MutableList<ToDoModel>) : this(MainActivity()) {

    }

    private lateinit var todoList: MutableList<ToDoModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    fun setTasks(toDolist: MutableList<ToDoModel>) {
        this.todoList = toDolist
    }

    override fun getItemCount(): Int {
        return if (::todoList.isInitialized) todoList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoModel: ToDoModel = todoList[position]
        holder.task.isChecked = todoModel.getStatus()
        holder.task.text = todoModel.getTask()
        // Bind the todoModel data to the view holder
    }

    fun setTodoList(todoList: MutableList<ToDoModel>) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task: CheckBox = itemView.findViewById<CheckBox>(R.id.todo_checkbox)
    }
}