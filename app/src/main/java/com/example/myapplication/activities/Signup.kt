package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var signUpBtn: Button
    private lateinit var login : TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = Firebase.auth

        signUpBtn = findViewById(R.id.button)
        login = findViewById(R.id.textView1)

        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        signUpBtn.setOnClickListener {
            performSignUP()
        }

    }

    private fun performSignUP() {
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword2)

        if (email.text.isEmpty()|| password.text.isEmpty() ){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()


                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }

            }.addOnFailureListener{
                Toast.makeText(this,"Error occurred ${it.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }


}
