package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adaptor.incomeAdaptor
import com.example.myapplication.models.incomeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class Income_fetch1 : AppCompatActivity() {

    private lateinit var incomeRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var  incomeList : ArrayList<incomeModel>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_fetch1)

        incomeRecyclerView=findViewById(R.id.rvIncome)
        incomeRecyclerView.layoutManager= LinearLayoutManager(this)
        incomeRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.loadingData)

        incomeList = arrayListOf<incomeModel>()

        getIncomesData()
    }
    private fun getIncomesData() {
        incomeRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("Incomes")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                incomeList.clear()
                if(snapshot.exists()){
                    for(incomesnap in snapshot.children){
                        val incomeData = incomesnap.getValue(incomeModel::class.java)
                        incomeList.add(incomeData!!)
                    }
                    val mAdaptor = incomeAdaptor(incomeList)
                    incomeRecyclerView.adapter=mAdaptor

                    mAdaptor.setOnItemClickListner(object :incomeAdaptor.onItemClickListner{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@Income_fetch1, Income::class.java)

                            //put extras
                            intent.putExtra("incomeId",incomeList[position].incomeId)
                            intent.putExtra("incomeType",incomeList[position].incomeType)
                            intent.putExtra("incomeAmount",incomeList[position].incomeAmount)
                            intent.putExtra("incomeDate",incomeList[position].incomeDate)
                            intent.putExtra("incomeComment",incomeList[position].incomeComment)
                            startActivity(intent)
                        }
                    })

                    incomeRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}