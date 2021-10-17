package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodo(){
        todos.removeAll{todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(txtItem: TextView, isChecked: Boolean){
        if(isChecked) txtItem.paintFlags = txtItem.paintFlags or STRIKE_THRU_TEXT_FLAG
        else txtItem.paintFlags = txtItem.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.itemView.apply {
            val txtItem = holder.itemView.findViewById<TextView>(R.id.txtItem)
            val cbDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
            txtItem.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(txtItem, cbDone.isChecked)
            cbDone.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(txtItem, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}