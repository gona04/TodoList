package com.example.todolist.viewmodel

import ToDoModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

     val todoMutableLiveData: MutableLiveData<ToDoModel> = MutableLiveData()
}
