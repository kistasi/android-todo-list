package com.kistasi.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoAdapter = TodoAdapter(mutableListOf())
        val list = findViewById<RecyclerView>(R.id.rvList)
        list.adapter = todoAdapter
        list.layoutManager = LinearLayoutManager(this)

        val todoEditText = findViewById<EditText>(R.id.etEdit)
        val addButton = findViewById<Button>(R.id.btnAddItem)
        addButton.setOnClickListener {
            val todoText = todoEditText.text.toString()
            if (todoText.isNotEmpty()) {
                val todo = Todo(todoText)
                todoAdapter.addItem(todo)
                todoEditText.text.clear()
            }
        }

        val deleteButton = findViewById<Button>(R.id.btnDelete)
        deleteButton.setOnClickListener {
            todoAdapter.deleteItems()
        }
    }
}
