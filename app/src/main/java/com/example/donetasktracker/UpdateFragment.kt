package com.example.donetasktracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class UpdateFragment : Fragment() {
    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var taskToEdit: String
    val args: UpdateFragmentArgs by navArgs()
    private lateinit var adapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskToEdit = it.getString("taskToEdit").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskToEdit = args.taskToEdit
        view.findViewById<EditText>(R.id.title_edit).setText(taskToEdit)
        //pass back the edited task
        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val newlyEditedTask = view.findViewById<EditText>(R.id.title_edit).text.toString()
            Log.d("Kieran", "before heading back to listfragment $newlyEditedTask" )
            // Go back to ListFragment
            viewModel.tasks[viewModel.lastPositionClickedOn] = newlyEditedTask
            val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment(newlyEditedTask)
            findNavController().navigate(action)


        }



    }


}


