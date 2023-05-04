package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R

class Expenses : AppCompatActivity() {
    private lateinit var tvBillId: TextView
    private lateinit var tvBillType: TextView
    private lateinit var tvBillAmount: TextView
    private lateinit var tvBillDate: TextView
    private lateinit var tvBillComment: TextView
    private lateinit var updateBtn: Button
    private lateinit var deletebtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        initView()
        setValuesToViews()
    }

    private fun initView() {}

    private fun setValuesToViews(){

        tvBillId.text = intent.getStringExtra("billId")
        tvBillType.text = intent.getStringExtra("billType")
        tvBillAmount.text = intent.getStringExtra("billAmount")
        tvBillDate.text = intent.getStringExtra("billDate")
        tvBillComment.text = intent.getStringExtra("billComment")

    }
}