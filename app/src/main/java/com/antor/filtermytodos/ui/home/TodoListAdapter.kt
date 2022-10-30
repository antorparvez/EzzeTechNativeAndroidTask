package com.antor.filtermytodos.ui.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antor.domain.model.TodoModel
import com.antor.filtermytodos.databinding.VhTodoItemBinding


class TodoListAdapter(
    private val context: Context,
    private var todoList: ArrayList<TodoModel>,

    ) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(private val binding: VhTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TodoModel) {
            binding.taskId.text = "Task ID: ${data.id}"
            binding.userId.text = "UserID: ${data.userId}"
            if (data.completed){
                binding.taskStatus.text ="Task Completed"
                binding.taskStatus.setTextColor(Color.parseColor("#6200EE"))
            }else{

                binding.taskStatus.text ="Task isn't complete yet"
                binding.taskStatus.setTextColor(Color.parseColor("#FF0000"))

            }

            binding.taskTitle.text = data.title

        }

    }

    fun updateAdapter(newRetailerList: List<TodoModel>) {
        todoList.clear()
        todoList.addAll(newRetailerList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding =
            VhTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(data = todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}




