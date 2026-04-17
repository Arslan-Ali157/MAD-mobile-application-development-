package com.example.task5

import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DisplayContact : AppCompatActivity() {
    private lateinit var mydb: DBHelper
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var street: EditText
    private lateinit var place: EditText
    private var idToUpdate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_contact)

        name = findViewById(R.id.editTextName)
        phone = findViewById(R.id.editTextPhone)
        email = findViewById(R.id.editTextEmail)
        street = findViewById(R.id.editTextStreet)
        place = findViewById(R.id.editTextCity)

        mydb = DBHelper(this)

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getInt("id")
            if (value > 0) {
                // means this is the view part not the add contact part.
                val rs = mydb.getData(value)
                idToUpdate = value
                if (rs.moveToFirst()) {
                    val nameIndex = rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME)
                    val phoneIndex = rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE)
                    val emailIndex = rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL)
                    val streetIndex = rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STREET)
                    val placeIndex = rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_CITY)

                    if (nameIndex >= 0) name.setText(rs.getString(nameIndex))
                    if (phoneIndex >= 0) phone.setText(rs.getString(phoneIndex))
                    if (emailIndex >= 0) email.setText(rs.getString(emailIndex))
                    if (streetIndex >= 0) street.setText(rs.getString(streetIndex))
                    if (placeIndex >= 0) place.setText(rs.getString(placeIndex))

                    name.isFocusable = false
                    name.isClickable = false
                    phone.isFocusable = false
                    phone.isClickable = false
                    email.isFocusable = false
                    email.isClickable = false
                    street.isFocusable = false
                    street.isClickable = false
                    place.isFocusable = false
                    place.isClickable = false

                    findViewById<Button>(R.id.buttonSave).visibility = View.INVISIBLE
                }
                rs.close()
            }
        }

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val extras = intent.extras
            if (extras != null) {
                val value = extras.getInt("id")
                if (value > 0) {
                    if (mydb.updateContact(idToUpdate, name.text.toString(), phone.text.toString(), email.text.toString(), street.text.toString(), place.text.toString())) {
                        Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "not Updated", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (mydb.insertContact(name.text.toString(), phone.text.toString(), email.text.toString(), street.text.toString(), place.text.toString())) {
                        Toast.makeText(applicationContext, "done", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "not done", Toast.LENGTH_SHORT).show()
                    }
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getInt("id")
            if (value > 0) {
                menuInflater.inflate(R.menu.display_contact, menu)
            } else {
                menuInflater.inflate(R.menu.mainmenu, menu)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Edit_Contact -> {
                findViewById<Button>(R.id.buttonSave).visibility = View.VISIBLE
                name.isFocusableInTouchMode = true
                phone.isFocusableInTouchMode = true
                email.isFocusableInTouchMode = true
                street.isFocusableInTouchMode = true
                place.isFocusableInTouchMode = true
                true
            }
            R.id.Delete_Contact -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes") { _, _ ->
                        mydb.deleteContact(idToUpdate)
                        Toast.makeText(applicationContext, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("No") { _, _ -> }
                builder.create().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
