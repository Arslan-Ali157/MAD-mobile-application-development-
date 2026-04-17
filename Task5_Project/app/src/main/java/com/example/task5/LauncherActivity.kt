package com.example.task5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        findViewById<Button>(R.id.btnActivity1).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<Button>(R.id.btnActivity2).setOnClickListener {
            startActivity(Intent(this, ContactMainActivity::class.java))
        }

        findViewById<Button>(R.id.btnActivity3).setOnClickListener {
            startActivity(Intent(this, TodoActivity::class.java))
        }
    }
}
