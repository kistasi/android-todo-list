package com.kistasi.todolist

import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val items: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView
        val cbDone: CheckBox

        init {
            tvItem = view.findViewById(R.id.tvItem)
            cbDone = view.findViewById(R.id.cbDone)
        }
    }

    fun addItem(todo: Todo) {
        items.add(todo)
        notifyItemInserted(items.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItems() {
        items.removeAll { todo -> todo.isDone }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    private fun toggleStrikeThrough(tvList: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvList.paintFlags = tvList.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvList.paintFlags = tvList.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(todoViewHolder: TodoViewHolder, position: Int) {
        val currentItem = items[position]

        todoViewHolder.tvItem.text = currentItem.text
        todoViewHolder.cbDone.isChecked = currentItem.isDone

        toggleStrikeThrough(todoViewHolder.tvItem, todoViewHolder.cbDone.isChecked)

        todoViewHolder.cbDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(todoViewHolder.tvItem, isChecked)
            currentItem.isDone = !currentItem.isDone
        }
    }
}
