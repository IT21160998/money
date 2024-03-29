package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PickCategory : AppCompatActivity() {
    private lateinit var add: Button
    private lateinit var fetch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickcategory)

        add  = findViewById(R.id.button3)
        fetch= findViewById(R.id.button2)

        add.setOnClickListener{
            val intent = Intent(this, Bill::class.java)
            startActivity(intent)
        }
        fetch.setOnClickListener{
            val intent = Intent(this, fetchingActivity::class.java)
            startActivity(intent)
        }
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}
