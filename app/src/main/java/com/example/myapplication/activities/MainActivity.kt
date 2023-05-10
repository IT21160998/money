
package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var Income : Button
    private lateinit var Expenses : Button
    private lateinit var Savings : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Income = findViewById(R.id.button)
        Expenses = findViewById(R.id.Button2)
        Savings = findViewById(R.id.Button3)


        Income.setOnClickListener {
            val intent = Intent(this, activity_income_ch1::class.java)
            startActivity(intent)
        }

        Expenses.setOnClickListener {
            val intent = Intent(this, PickCategory::class.java)
            startActivity(intent)
        }

        Savings.setOnClickListener {
            val intent = Intent(this, Saving_main::class.java)

            startActivity(intent)
        }

        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()

    }
}