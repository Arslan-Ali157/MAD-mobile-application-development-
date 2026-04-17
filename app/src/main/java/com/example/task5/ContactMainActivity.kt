package com.example.task5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ContactMainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_main)

        dbHelper = DBHelper(this)
        
        listView = findViewById(R.id.listView1)
        displayContacts()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // In this specific tutorial pattern, ID is position + 1
            val idToSearch = position + 1
            val dataBundle = Bundle()
            dataBundle.putInt("id", idToSearch)
            val intent = Intent(applicationContext, DisplayContact::class.java)
            intent.putExtras(dataBundle)
            startActivity(intent)
        }
    }

    private fun displayContacts() {
        val arrayList = dbHelper.getAllContacts()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = arrayAdapter
    }

    override fun onResume() {
        super.onResume()
        displayContacts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Step 9: Create a new menu as res/menu/mainmenu.xml
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                val dataBundle = Bundle()
                dataBundle.putInt("id", 0)
                val intent = Intent(applicationContext, DisplayContact::class.java)
                intent.putExtras(dataBundle)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
