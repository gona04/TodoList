package com.example.todolist.viewmodel

import ToDoModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {

     val todoMutableLiveData: MutableLiveData<ToDoModel> = MutableLiveData()
}
