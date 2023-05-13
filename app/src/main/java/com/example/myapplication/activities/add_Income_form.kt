package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.models.BillModel
import com.example.myapplication.models.incomeModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add_Income_form : AppCompatActivity() {
    private lateinit var incometype: EditText
    private lateinit var amount: EditText
    private lateinit var date: EditText
    private lateinit var comment: EditText
    private lateinit var addincome: Button

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income_form)

        incometype = findViewById(R.id.editText2)
        amount = findViewById(R.id.editText3)
        date = findViewById(R.id.editTextDate)
        comment = findViewById(R.id.editText4)
        addincome = findViewById(R.id.button)

        dbRef= FirebaseDatabase.getInstance().getReference("Income")

        addincome.setOnClickListener{
            saveIncomeData()
    }
}

    private fun saveIncomeData(){
        val incomeType = incometype.text.toString()
        val incomeAmount = amount.text.toString()
        val incomeDate = date.text.toString()
        val incomeComment = comment.text.toString()

        if(incomeType.isEmpty()){
            incometype.error = "Please enter income type"
        }else if(incomeAmount.isEmpty()){
            amount.error = "Please enter amount"
        }else if(incomeDate.isEmpty()){
            date.error ="Please enter date"
        }else if(incomeComment.isEmpty()){
            comment.error = "Add comment"
        }else {

            val incomeId = dbRef.push().key!!

            val income = incomeModel(incomeId, incomeType, incomeAmount, incomeDate, incomeComment)

            dbRef.child(incomeId).setValue(income)
                .addOnCompleteListener {
                    Toast.makeText(
                        this, "Data inserted Successfully", Toast.LENGTH_LONG
                    ).show()

                    incometype.text.clear()
                    amount.text.clear()
                    date.text.clear()
                    comment.text.clear()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error $err.get", Toast.LENGTH_LONG).show()
                }
        }
    }
}