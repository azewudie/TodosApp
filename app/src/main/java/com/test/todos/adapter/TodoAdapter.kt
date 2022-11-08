package com.test.todos.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.todos.databinding.ListTodosBinding
import com.test.todos.models.data.Todos
import com.test.todos.models.data.TodosItem
import kotlin.math.log

class TodoAdapter(var todoItem:Todos):RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    class ViewHolder(private val binding:ListTodosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBinding(dataItem: TodosItem) {
            binding.tvUserId.text = "User ID:${dataItem.userId.toString()}"
            binding.tvId.text = "ID:${dataItem.id.toString()}"
            binding.tvTitle.text = "Title:${dataItem.title}"
            binding.tvCompleted.text = "Completed:${dataItem.completed.toString()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return TodoAdapter.ViewHolder(
            ListTodosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBinding(todoItem[position])
    }

    override fun getItemCount(): Int {
        Log.d("sizeItem", "getItemCount: ${todoItem.size}")
        Log.d("itemList", "getItemCount:$todoItem ")
       return todoItem.size

    }
}