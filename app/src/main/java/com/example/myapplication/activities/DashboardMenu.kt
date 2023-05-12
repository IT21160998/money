package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DashboardMenu : AppCompatActivity() {
    private lateinit var Home : Button
    private lateinit var Setting : Button
    private lateinit var reminder : Button
    private lateinit var monthly_plan : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_menu)

        Home = findViewById(R.id.button7)
        Setting = findViewById(R.id.button8)
        reminder = findViewById(R.id.button9)
        monthly_plan = findViewById(R.id.button6)


        Home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Setting.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        reminder.setOnClickListener {
            val intent = Intent(this, Reminder::class.java)
            startActivity(intent)
        }

        monthly_plan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}