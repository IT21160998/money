package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.content.Intent
import com.example.myapplication.R
import android.widget.TextView
import android.view.animation.Animation

class Mainmain : AppCompatActivity() {
    private lateinit var strat : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmain)

        strat = findViewById(R.id.button)

        strat.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }
}