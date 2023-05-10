package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Savingmain : AppCompatActivity() {
<<<<<<<<< Temporary merge branch 1

    private lateinit var add :  Button
=========
    private lateinit var add : Button
>>>>>>>>> Temporary merge branch 2
    private lateinit var fetch : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savingmain)

        add = findViewById(R.id.button)
        fetch = findViewById(R.id.button1)

        add.setOnClickListener {
<<<<<<<<< Temporary merge branch 1
            val intent = Intent(this, Bill::class.java)
            startActivity(intent)
        }
        fetch.setOnClickListener {
            val intent = Intent(this, fetchingActivity::class.java)
            startActivity(intent)
        }
        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
=========
            val intent = Intent(this, AddSavings::class.java)
            startActivity(intent)
        }

        fetch.setOnClickListener {
            val intent = Intent(this,AddSavings::class.java)
            startActivity(intent)
        }
>>>>>>>>> Temporary merge branch 2
    }
}
