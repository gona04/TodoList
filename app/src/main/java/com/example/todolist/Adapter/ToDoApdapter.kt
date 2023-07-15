package com.example.todolist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.MainActivity
import com.example.todolist.Model.ToDoModel
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding

class ToDoAdapter(private val activity: MainActivity) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private lateinit var todoList: List<ToDoModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoModel: ToDoModel = todoList[position]
        holder.task.isChecked = todoModel.getStatus()
        holder.task.text = todoModel.getTask()
        // Bind the todoModel data to the view holder
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task: CheckBox

        init {
            task = itemView.findViewById<CheckBox>(R.id.todo_checkbox)
        }
    }

}
