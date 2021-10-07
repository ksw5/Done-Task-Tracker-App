package com.example.donetasktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class AddFragment : Fragment() {
    var adapter: TaskAdapter? = null
    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var taskToAdd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskToAdd = it.getString("taskToAdd").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.addButton).setOnClickListener{
            // Grab the text in the edittext
            val newAddedTask = view.findViewById<EditText>(R.id.title_add).text.toString()
            viewModel.tasks.add(newAddedTask)
            adapter?.notifyItemChanged(viewModel.tasks.size - 1)

            // Go back to ListFragment
            val action = AddFragmentDirections.actionAddFragmentToListFragment(newAddedTask)
            adapter?.notifyItemChanged(viewModel.tasks.size - 1)
            findNavController().navigate(action)
        }
    }
}