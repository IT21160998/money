package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.models.BillModel
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

class Bill : AppCompatActivity() {
        private lateinit var billtype: EditText
        private lateinit var amount: EditText
        private lateinit var date: EditText
        private lateinit var comment: EditText
        private lateinit var addbill:Button

        private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

            billtype = findViewById(R.id.editText2)
            amount = findViewById(R.id.editText3)
            date = findViewById(R.id.editTextDate)
            comment = findViewById(R.id.editText4)
            addbill = findViewById(R.id.button)

            dbRef= FirebaseDatabase.getInstance().getReference("Bills")

            addbill.setOnClickListener{
                saveBillData()
            }
    }
            private fun saveBillData(){
                val billType = billtype.text.toString()
                val billAmount = amount.text.toString()
                val billDate = date.text.toString()
                val billComment = comment.text.toString()

                if(billType.isEmpty()){
                    billtype.error = "Please enter bill name"
                }

                if(billAmount.isEmpty()){
                    amount.error = "Please enter bill amount"
                }

                if(billDate.isEmpty()){
                    date.error ="Please enter bill date"
                }

                if(billComment.isEmpty()){
                    comment.error = "Add comment"
                }

                val billId = dbRef.push().key!!

                val bills = BillModel(billId,billType,billAmount,billComment)

                dbRef.child(billId).setValue(bills)
                    .addOnCompleteListener {
                        Toast.makeText(this,"Data inserted Successfully",Toast.LENGTH_LONG
                        ).show()

                        billtype.text.clear()
                        amount.text.clear()
                        date.text.clear()
                        comment.text.clear()

                    }.addOnFailureListener{err ->
                        Toast.makeText(this,"Error $err.get",Toast.LENGTH_LONG).show()
                    }
            }
}