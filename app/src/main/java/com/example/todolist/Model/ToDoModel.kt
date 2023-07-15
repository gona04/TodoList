package com.example.todolist.Model

class ToDoModel(
    private var id: Int,
    private var status: Boolean,
    private var task: String) {

    constructor() : this(0, false , "") {
    }

    public fun setId(id: Int) {
        this.id = id
    }

    public fun setStatus(status: Boolean) {
        this.status = status
    }

    public fun setTask(task:String) {
        this.task = task
    }

    public fun getId():Int {
        return this.id
    }

    public fun getStatus():Boolean {
        return this.status
    }

    public fun getTask():String {
        return this.task
    }
 }