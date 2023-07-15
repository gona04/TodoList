import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoModel : ViewModel() {
    private var id: Int = 0
    private var status: Boolean = false
    private var task: String = ""

    private val todoLiveData: MutableLiveData<ToDoModel> = MutableLiveData()

    fun setId(id: Int) {
        this.id = id
    }

    fun setStatus(status: Boolean) {
        this.status = status
    }

    fun setTask(task: String) {
        this.task = task
    }

    fun getId(): Int {
        return this.id
    }

    fun getStatus(): Boolean {
        return this.status
    }

    fun getTask(): String {
        return this.task
    }

    fun getTodoLiveData(): LiveData<ToDoModel> {
        return todoLiveData
    }

    fun updateTodoLiveData() {
        todoLiveData.value = this
    }
}
