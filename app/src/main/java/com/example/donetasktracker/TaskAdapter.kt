package com.example.donetasktracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<String>,
                  private val onItemClickListener: OnItemClickedListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    interface OnItemClickedListener {
        fun onItemClicked(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_txt)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val taskItemView = inflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(taskItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks.get(position)
        holder.title.text = task
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}