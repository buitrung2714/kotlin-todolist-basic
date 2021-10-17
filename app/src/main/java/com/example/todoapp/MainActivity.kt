package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvTodoList = findViewById<RecyclerView>(R.id.rvTodoList)
        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        val btnDeleteTodo = findViewById<Button>(R.id.btnDeleteDoneTodos)
        val etToTodoTitle = findViewById<EditText>(R.id.etToTodoTitle)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoList.adapter = todoAdapter
        rvTodoList.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etToTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etToTodoTitle.text.clear()
            }
        }

        btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteTodo()
        }
    }
}