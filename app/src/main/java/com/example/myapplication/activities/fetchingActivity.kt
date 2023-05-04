package com.example.myapplication.activities

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adaptor.billAdaptor
import com.example.myapplication.models.BillModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class fetchingActivity : AppCompatActivity() {

    private lateinit var billRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var  billList : ArrayList<BillModel>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        billRecyclerView=findViewById(R.id.rvBill)
        billRecyclerView.layoutManager= LinearLayoutManager(this)
        billRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.loadingData)

        billList = arrayListOf(BillModel())

        getBillsData()
    }
    private fun getBillsData() {
        billRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("Bills")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                billList.clear()
                if(snapshot.exists()){
                    for(billsnap in snapshot.children){
                        val billData = billsnap.getValue(BillModel::class.java)
                        billList.add(billData!!)
                    }
                    val mAdaptor = billAdaptor(billList)
                    billRecyclerView.adapter=mAdaptor

                    mAdaptor.setOnItemClickListner(object :billAdaptor.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@fetchingActivity,Expenses::class.java)
                            intent.putExtra("billId",billList[position].billId)
                            intent.putExtra("billType",billList[position].billType)
                            intent.putExtra("billAmount",billList[position].billAmount)
                            intent.putExtra("billDate",billList[position].billDate)
                            intent.putExtra("billComment",billList[position].billComment)
                            startActivity(intent)
                        }
                    })

                    billRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}