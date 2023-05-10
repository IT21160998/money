package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import com.example.myapplication.R
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.models.savingModel
import com.google.firebase.database.FirebaseDatabase


class Savings : AppCompatActivity() {

    private lateinit var tvSavingId: TextView
    private lateinit var tvSavingType: TextView
    private lateinit var tvSavingAmount: TextView
    private lateinit var tvSavingDate: TextView
    private lateinit var tvSavingComment: TextView
    private lateinit var updatebtn: Button
    private lateinit var deletebtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saving)

        initView()
        setValuesToViews()

        updatebtn.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("SavingId").toString(),
                intent.getStringExtra("SavingType").toString()
            )
        }
        deletebtn.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("SavingId").toString()
            )
        }
    }
    private fun initView() {
        tvSavingId= findViewById(R.id.tvSavingId)
        tvSavingType=findViewById(R.id.tvSavingType)
        tvSavingAmount=findViewById(R.id.tvSavingAmount)
        tvSavingDate = findViewById(R.id.tvSavingDate)
        tvSavingComment=findViewById(R.id.tvSavingComment)

        updatebtn = findViewById(R.id.updatebtn)
        deletebtn = findViewById(R.id.deletebtn)
    }

    private fun setValuesToViews(){

        tvSavingId.text = intent.getStringExtra("SavingId")
        tvSavingType.text = intent.getStringExtra("SavingType")
        tvSavingAmount.text = intent.getStringExtra("SavingAmount")
        tvSavingDate.text = intent.getStringExtra("SavingDate")
        tvSavingComment.text = intent.getStringExtra("SavingComment")

    }
    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Savings").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Saving details deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, feching3::class.java)
            finish()
            startActivity(intent)

        }.addOnFailureListener{error->
            Toast.makeText(this,"Deleting error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(
        savingId:String,
        savingType:String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_saving,null)

        mDialog.setView(mDialogView)
        val etsavingType = mDialogView.findViewById<EditText>(R.id.etSavingType)
        val etsavingAmount = mDialogView.findViewById<EditText>(R.id.etSavingAmount)
        val etsavingDate = mDialogView.findViewById<EditText>(R.id.etSavingDate)
        val etsavingComment = mDialogView.findViewById<EditText>(R.id.etSavingComment)
        val updateBtn = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etsavingType.setText(intent.getStringExtra("savingType").toString())
        etsavingAmount.setText(intent.getStringExtra("savingAmount").toString())
        etsavingDate.setText(intent.getStringExtra("savingDate").toString())
        etsavingComment.setText(intent.getStringExtra("savingComment").toString())

        mDialog.setTitle("Updating $savingType Record")

        val alertDialog=mDialog.create()
        alertDialog.show()

        updateBtn.setOnClickListener{
            updatesavingData(
                savingId,
                etsavingType.text.toString(),
                etsavingAmount.text.toString(),
                etsavingDate.text.toString(),
                etsavingComment.text.toString()
            )

            Toast.makeText(applicationContext,"Saving Data Updated", Toast.LENGTH_LONG)

            //setting updated data
            tvSavingType.text = etsavingType.text.toString()
            tvSavingAmount.text = etsavingAmount.text.toString()
            tvSavingDate.text = etsavingDate.text.toString()
            tvSavingComment.text = etsavingComment.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updatesavingData(
        id: String,
        type: String,
        Amount:String,
        Date:String,
        Comment:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Savings").child(id)
        val savingInfo = savingModel(id,type,Amount,Date,Comment)
        dbRef.setValue(savingInfo)
    }
}