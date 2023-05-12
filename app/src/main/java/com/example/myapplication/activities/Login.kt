package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R

class Login : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    private lateinit var createaccount : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        email = findViewById(R.id.editText1)
        password = findViewById(R.id.editText2)
        login = findViewById(R.id.button)
        createaccount = findViewById(R.id.button1)


        createaccount.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

    }
}