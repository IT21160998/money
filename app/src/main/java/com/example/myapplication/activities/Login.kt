package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login : Button
    private lateinit var createaccount : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth


        email = findViewById(R.id.editText1)
        password = findViewById(R.id.editText2)
        login = findViewById(R.id.button)
        createaccount = findViewById(R.id.button1)


        createaccount.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            performLogin()
        }
    }
    private fun performLogin(){
        val email : EditText = findViewById(R.id.editText1)
        val password : EditText = findViewById(R.id.editText2)

        if (email.text.isEmpty()|| password.text.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show()
            return
        }

        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput,passwordInput)
            .addOnCompleteListener (this){task ->
                if(task.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()

                } else{
                    Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext,"Authentication ailed ${it.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }
}