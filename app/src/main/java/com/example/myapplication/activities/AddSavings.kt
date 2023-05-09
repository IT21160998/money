package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference

class AddSavings : AppCompatActivity() {
    private lateinit var savingtype: EditText
    private lateinit var amount: EditText
    private lateinit var date: EditText
    private lateinit var comment: EditText
    private lateinit var addSavings: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_savings)

        savingtype =findViewById(R.id.editText2)
        amount =findViewById(R.id.editText3)
        date =findViewById(R.id.editTextDate)
        comment =findViewById(R.id.editText4)
        addSavings = findViewById(R.id.button)
    }
}