package com.example.greenpulse_04

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter : ListAdapter<FarmingTask, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleView: TextView? = view.findViewById(R.id.txtTaskTitle)
        private val chkTaskCompleted: CheckBox = view.findViewById(R.id.chkTaskCompleted)

        fun bind(task: FarmingTask) {
            titleView?.text = task.title ?: "No Title"
            chkTaskCompleted.isChecked = task.isCompleted

            // Apply strike-through text effect when checked
            if (task.isCompleted) {
                titleView?.paintFlags = titleView?.paintFlags?.or(Paint.STRIKE_THRU_TEXT_FLAG)!!
            } else {
                titleView?.paintFlags = titleView?.paintFlags?.and(Paint.STRIKE_THRU_TEXT_FLAG.inv())!!
            }

            // Update task completion status when checkbox state changes
            chkTaskCompleted.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    titleView?.paintFlags = titleView?.paintFlags!! or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    titleView?.paintFlags = titleView?.paintFlags!! and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                // Assuming you have a way to update the task status
                task.isCompleted = isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<FarmingTask>() {
    override fun areItemsTheSame(oldItem: FarmingTask, newItem: FarmingTask) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: FarmingTask, newItem: FarmingTask) = oldItem == newItem
}
