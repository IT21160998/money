package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class DashboardMenu : AppCompatActivity() {
    private lateinit var home : Button
    private lateinit var setting : Button
    private lateinit var reminder : Button
    private lateinit var monthlyplan : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_menu)

        home = findViewById(R.id.button7)
        setting = findViewById(R.id.button8)
        reminder = findViewById(R.id.button9)
        monthlyplan = findViewById(R.id.button6)


        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setting.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        reminder.setOnClickListener {
            val intent = Intent(this, addReminder::class.java)
            startActivity(intent)
        }

        monthlyplan.setOnClickListener {
            val intent = Intent(this, MonthlyPlan::class.java)

            startActivity(intent)
        }

    }
}