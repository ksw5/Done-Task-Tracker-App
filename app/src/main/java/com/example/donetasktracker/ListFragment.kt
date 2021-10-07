package com.example.donetasktracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.donetasktracker.databinding.FragmentListBinding
import java.text.SimpleDateFormat
import java.util.*

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    var adapter: TaskAdapter? = null
    private val viewModel: TaskViewModel by activityViewModels()
    val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskAdapter(viewModel.tasks, object : TaskAdapter.OnItemClickedListener {
                override fun onItemClicked(position: Int) {
                    val taskToEditString = viewModel.tasks[position]
                    Log.d("kieran", "task clicked on $taskToEditString")
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(taskToEditString)
                    viewModel.lastPositionClickedOn = position

                    view.findNavController().navigate(action)
                    val newlyEditedTask = args.newlyEditedTask
                    if (!newlyEditedTask.isNullOrEmpty()) {
                        viewModel.tasks[viewModel.lastPositionClickedOn] = newlyEditedTask
                        adapter?.notifyDataSetChanged()
                    }

                }

            })



        binding.apply {
            date.text = SimpleDateFormat("EEEE, MMMM dd, yyyy").format(Date())
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            floatingActionButton.setOnClickListener {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())

            }
            recyclerView.setHasFixedSize(true)

        }

        // This code causes app to crash on startup, but I need a way to update
        // to the newly edited task



    }


    }