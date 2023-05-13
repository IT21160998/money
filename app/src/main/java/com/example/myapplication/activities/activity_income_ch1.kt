package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class activity_income_ch1 : AppCompatActivity() {

    private lateinit var add: Button
    private lateinit var fetch : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_ch1)

        add  = findViewById(R.id.button5)
        fetch= findViewById(R.id.button4)

        add.setOnClickListener{
            val intent = Intent(this, add_Income_form::class.java)
            startActivity(intent)
        }
        fetch.setOnClickListener{
            val intent = Intent(this, Income_fetch1::class.java)
            startActivity(intent)
        }
    }
}



