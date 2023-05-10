package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.models.savingModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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

        dbRef= FirebaseDatabase.getInstance().getReference("Savings")

        addSavings.setOnClickListener{
            saveSavingData()
    }
}
        private fun saveSavingData(){
            val savingType = savingtype.text.toString()
            val savingAmount = amount.text.toString()
            val savingDate = date.text.toString()
            val savingComment = comment.text.toString()

            if(savingType.isEmpty()){
                savingtype.error  = "Please enter income name"
            }

            if(savingAmount.isEmpty()){
                amount.error = "Please enter amount"
                }

            if(savingDate.isEmpty()){
                date.error = "Please enter date"
            }

            if(savingComment.isEmpty()){
                comment.error = "Please enter comment"
            }


            val savingId = dbRef.push().key!!

            val savings = savingModel(savingId,savingType,savingAmount,savingDate,savingComment)


            dbRef.child(savingId).setValue(savings)
                .addOnCompleteListener {
                    Toast.makeText(this,"Data inserted Successfully", Toast.LENGTH_LONG
                    ).show()

                    savingtype.text.clear()
                    amount.text.clear()
                    date.text.clear()
                    comment.text.clear()

                }.addOnFailureListener{err ->
                    Toast.makeText(this,"Error $err.get", Toast.LENGTH_LONG).show()
                }
        }
}