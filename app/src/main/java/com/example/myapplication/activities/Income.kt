package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.example.myapplication.models.BillModel
import com.example.myapplication.models.incomeModel
import com.google.firebase.database.FirebaseDatabase

class Income : AppCompatActivity() {

    private lateinit var tvIncomeId: TextView
    private lateinit var tvIncomeType: TextView
    private lateinit var tvIncomeAmount: TextView
    private lateinit var tvIncomeDate: TextView
    private lateinit var tvIncomeComment: TextView
    private lateinit var updatebtn: Button
    private lateinit var deletebtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        initView()
        setValuesToViews()

        updatebtn.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("IncomeId").toString(),
                intent.getStringExtra("IncomeType").toString()
            )
        }
        deletebtn.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("IncomeId").toString()
            )
        }
    }
    private fun deleteRecord(
        id: String
    ){
        val dbref = FirebaseDatabase.getInstance().getReference("Incomes").child(id)
        val mTask = dbref.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(
                this, "Income details deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Income_fetch1::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Deleting error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvIncomeId= findViewById(R.id.tvIncomeId)
        tvIncomeType=findViewById(R.id.tvIncomeType)
        tvIncomeAmount=findViewById(R.id.tvIncomeAmount)
        tvIncomeDate = findViewById(R.id.tvIncomeDate)
        tvIncomeComment=findViewById(R.id.tvIncomeComment)

        updatebtn = findViewById(R.id.updatebtn)
        deletebtn = findViewById(R.id.deletebtn)
    }

    private fun setValuesToViews(){

        tvIncomeId.text = intent.getStringExtra("IncomeId")
        tvIncomeType.text = intent.getStringExtra("IncomeType")
        tvIncomeAmount.text = intent.getStringExtra("IncomeAmount")
        tvIncomeDate.text = intent.getStringExtra("IncomeDate")
        tvIncomeComment.text = intent.getStringExtra("IncomeComment")
    }

    private fun openUpdateDialog(
        IncomeId:String,
        IncomeType:String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_income,null)

        mDialog.setView(mDialogView)
        val etIncomeType = mDialogView.findViewById<EditText>(R.id.etIncomeType)
        val etIncomeAmount = mDialogView.findViewById<EditText>(R.id.etIncomeAmount)
        val etIncomeDate = mDialogView.findViewById<EditText>(R.id.etIncomeDate)
        val etIncomeComment = mDialogView.findViewById<EditText>(R.id.etIncomeComment)
        val updateBtn = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etIncomeType.setText(intent.getStringExtra("IncomeType").toString())
        etIncomeAmount.setText(intent.getStringExtra("IncomeAmount").toString())
        etIncomeDate.setText(intent.getStringExtra("IncomeDate").toString())
        etIncomeComment.setText(intent.getStringExtra("IncomeComment").toString())

        mDialog.setTitle("Updating $IncomeType Record")

        val alertDialog=mDialog.create()
        alertDialog.show()

        updateBtn.setOnClickListener{
            updateIncomeData(
                IncomeId,
                etIncomeType.text.toString(),
                etIncomeAmount.text.toString(),
                etIncomeDate.text.toString(),
                etIncomeComment.text.toString()
            )

            Toast.makeText(applicationContext,"Income Data Updated", Toast.LENGTH_LONG)

            //setting updated data
            tvIncomeType.text = etIncomeType.text.toString()
            tvIncomeAmount.text = etIncomeAmount.text.toString()
            tvIncomeDate.text = etIncomeDate.text.toString()
            tvIncomeComment.text = etIncomeComment.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateIncomeData(
        id: String,
        type: String,
        Amount:String,
        Date:String,
        Comment:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Incomes").child(id)
        val incomeInfo = incomeModel(id,type,Amount,Date,Comment)
        dbRef.setValue(incomeInfo)
    }

}