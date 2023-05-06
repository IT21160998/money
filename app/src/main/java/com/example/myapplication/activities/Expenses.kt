package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.FontResourcesParserCompat.FetchStrategy
import com.example.myapplication.R
import com.example.myapplication.models.BillModel
import com.google.firebase.database.FirebaseDatabase

class Expenses : AppCompatActivity() {

    private lateinit var tvBillId: TextView
    private lateinit var tvBillType: TextView
    private lateinit var tvBillAmount: TextView
    private lateinit var tvBillDate: TextView
    private lateinit var tvBillComment: TextView
    private lateinit var updatebtn: Button
    private lateinit var deletebtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        initView()
        setValuesToViews()

         updatebtn.setOnClickListener{
             openUpdateDialog(
                 intent.getStringExtra("billId").toString(),
                intent.getStringExtra("billType").toString()
             )
         }
         deletebtn.setOnClickListener{
             deleteRecord(
                 intent.getStringExtra("billId").toString()
             )
         }
    }
    private fun deleteRecord(
        id: String
    ){
        val dbref = FirebaseDatabase.getInstance().getReference("bills").child(id)
        val mTask = dbref.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(
                this, "Bill details deleted", Toast.LENGTH_LONG).show()

                val intent = Intent(this, fetchingActivity::class.java)
                finish()
                startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Deleting error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvBillId= findViewById(R.id.tvBillId)
        tvBillType=findViewById(R.id.tvBillType)
        tvBillAmount=findViewById(R.id.tvBillAmount)
        tvBillDate = findViewById(R.id.tvBillDate)
        tvBillComment=findViewById(R.id.tvBillComment)

        updatebtn = findViewById(R.id.updatebtn)
        deletebtn = findViewById(R.id.deletebtn)
    }

    private fun setValuesToViews(){

        tvBillId.text = intent.getStringExtra("billId")
        tvBillType.text = intent.getStringExtra("billType")
        tvBillAmount.text = intent.getStringExtra("billAmount")
        tvBillDate.text = intent.getStringExtra("billDate")
        tvBillComment.text = intent.getStringExtra("billComment")
    }

    private fun openUpdateDialog(
            billId:String,
            billType:String
    ) {
            val mDialog = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val mDialogView = inflater.inflate(R.layout.activity_update_bill,null)

            mDialog.setView(mDialogView)
                val etbillType = mDialogView.findViewById<EditText>(R.id.etBillType)
                val etbillAmount = mDialogView.findViewById<EditText>(R.id.etbillAmount)
                val etbillDate = mDialogView.findViewById<EditText>(R.id.etbillDate)
                 val etbillComment = mDialogView.findViewById<EditText>(R.id.etbillComment)
                val updateBtn = mDialogView.findViewById<Button>(R.id.btnUpdateData)

                etbillType.setText(intent.getStringExtra("billType").toString())
                etbillAmount.setText(intent.getStringExtra("billAmount").toString())
                etbillDate.setText(intent.getStringExtra("billDate").toString())
                etbillComment.setText(intent.getStringExtra("billComment").toString())

                mDialog.setTitle("Updating $billType Record")

                val alertDialog=mDialog.create()
                alertDialog.show()

                updateBtn.setOnClickListener{
                    updatebillData(
                        billId,
                        etbillType.text.toString(),
                        etbillAmount.text.toString(),
                        etbillDate.text.toString(),
                        etbillComment.text.toString()
                    )

                    Toast.makeText(applicationContext,"Bill Data Updated", Toast.LENGTH_LONG)

                    //setting updated data
                    tvBillType.text = etbillType.text.toString()
                    tvBillAmount.text = etbillAmount.text.toString()
                    tvBillDate.text = etbillDate.text.toString()
                    tvBillComment.text = etbillComment.text.toString()

                    alertDialog.dismiss()
                }
    }

    private fun updatebillData(
        id: String,
        type: String,
        Amount:String,
        Date:String,
        Comment:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Bills").child(id)
        val billInfo = BillModel(id,type,Amount,Date,Comment)
        dbRef.setValue(billInfo)
    }
}