import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.databinding.NewTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddNewTask(todo: ToDoModel) : BottomSheetDialogFragment() {
    private var _viewBinding: NewTaskBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var todo: ToDoModel

    constructor():this(ToDoModel()) {

    }

    companion object {
        const val TAG: String = "ActionButtonDialog"

        fun newInstance(todo: ToDoModel): AddNewTask {
            return AddNewTask(todo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activity = requireActivity()
        todo = ViewModelProvider(this).get(ToDoModel::class.java)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = NewTaskBinding.inflate(inflater, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bundle: Bundle? = arguments
        var taskText = viewBinding.newTaskText
        var saveBtn = viewBinding.newTaskButton

        if (bundle != null) {
            var task: String? = bundle.getString("task")
            if (task?.length!! > 0) {
                val colorPrimary = ContextCompat.getColor(requireContext(), R.color.falcon)
                saveBtn.setTextColor(colorPrimary)
            }
        }

        taskText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implementation for beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Implementation for onTextChanged
                if (s.toString().isEmpty()) {
                    saveBtn.isEnabled = false
                    saveBtn.setTextColor(Color.GRAY)
                } else {
                    saveBtn.isEnabled = true
                    val colorPrimary = ContextCompat.getColor(requireContext(), R.color.falcon)
                    saveBtn.setTextColor(colorPrimary)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implementation for afterTextChanged
            }
        })

        saveBtn.setOnClickListener {
            todo.getTodoLiveData().observe(viewLifecycleOwner) { updatedTodo ->
                // Handle updatedTodo, which contains the latest data
                Log.d("FROM ADDNEWTASK", updatedTodo.getTask())
            }

            // Update the values in the ToDoModel
            todo.setTask(taskText.text.toString())
            todo.setStatus(false)
            todo.setId(1)
            todo.updateTodoLiveData()

            (activity as MainActivity).observeTodoLiveData()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
