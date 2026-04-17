package com.example.task5

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.util.*

class TodoActivity : AppCompatActivity() {
    private lateinit var items: ArrayList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var lvItems: ListView
    private lateinit var etNewItem: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        lvItems = findViewById(R.id.lvItems)
        etNewItem = findViewById(R.id.etNewItem)
        
        readItems()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvItems.adapter = itemsAdapter

        findViewById<Button>(R.id.btnAddItem).setOnClickListener {
            val itemText = etNewItem.text.toString()
            if (itemText.isNotEmpty()) {
                itemsAdapter.add(itemText)
                etNewItem.setText("")
                writeItems()
            }
        }

        setupListViewListener()
    }

    private fun setupListViewListener() {
        lvItems.setOnItemLongClickListener { _, _, position, _ ->
            items.removeAt(position)
            itemsAdapter.notifyDataSetChanged()
            writeItems()
            true
        }
    }

    private fun readItems() {
        val todoFile = File(filesDir, "todo.txt")
        items = try {
            val scanner = Scanner(todoFile)
            val list = ArrayList<String>()
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine())
            }
            scanner.close()
            list
        } catch (e: Exception) {
            ArrayList()
        }
    }

    private fun writeItems() {
        val todoFile = File(filesDir, "todo.txt")
        try {
            val fos = FileOutputStream(todoFile)
            fos.use {
                for (item in items) {
                    it.write((item + "\n").toByteArray())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
