import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.todolist.R
import com.example.todolist.databinding.NewTaskBinding
import com.example.todolist.viewmodel.ToDoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/*
* This fragment is used for adding new tasks.
*/
class AddNewTaskFragment(var viewModel: ToDoViewModel) : BottomSheetDialogFragment() {
    private var _viewBinding: NewTaskBinding? = null
    private val viewBinding get() = _viewBinding!!

    companion object {
        const val TAG: String = "ActionButtonDialog"

        // Factory method to create a new instance of AddNewTaskFragment
        fun newInstance(toDoViewModel: ToDoViewModel): AddNewTaskFragment {
            return AddNewTaskFragment(toDoViewModel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the style of the dialog fragment
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = NewTaskBinding.inflate(inflater, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve task data from arguments
        val bundle: Bundle? = arguments
        val taskText = viewBinding.newTaskText
        val saveBtn = viewBinding.newTaskButton

        if (bundle != null) {
            val task: String? = bundle.getString("task")
            if (task?.isNotEmpty() == true) {
                // Change the text color of the button if task is not empty
                val colorPrimary = ContextCompat.getColor(requireContext(), R.color.falcon)
                saveBtn.setTextColor(colorPrimary)
            }
        }

        // Add a text watcher to the taskText EditText to enable/disable the button and update its color
        taskText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implementation for beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Implementation for onTextChanged
                if (s.toString().isEmpty()) {
                    // Disable the button and set text color to gray if task text is empty
                    saveBtn.isEnabled = false
                    saveBtn.setTextColor(Color.GRAY)
                } else {
                    // Enable the button and set text color to the primary color
                    val colorPrimary = ContextCompat.getColor(requireContext(), R.color.falcon)
                    saveBtn.isEnabled = true
                    saveBtn.setTextColor(colorPrimary)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implementation for afterTextChanged
            }
        })

        // Set a click listener for the saveBtn to save the new task and dismiss the dialog
        saveBtn.setOnClickListener {
            // Update the values in the ToDoModel
            val data = ToDoModel(taskText.text.toString(), false)
            viewModel.todoMutableLiveData.value = data
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}