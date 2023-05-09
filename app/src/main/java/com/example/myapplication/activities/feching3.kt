package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adaptor.savingAdaptor
import com.example.myapplication.models.savingModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class feching3 : AppCompatActivity() {

    private lateinit var savingRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var  savingList : ArrayList<savingModel>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feching3)

        savingRecyclerView=findViewById(R.id.rvSaving)
        savingRecyclerView.layoutManager= LinearLayoutManager(this)
        savingRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.loadingData)

        savingList = arrayListOf<savingModel>()

        getsavingData()
    }
    private fun getsavingData() {
        savingRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef= FirebaseDatabase.getInstance().getReference("Savings")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                savingList.clear()
                if(snapshot.exists()){
                    for(savingsnap in snapshot.children){
                       val savingData = savingsnap.getValue(savingModel::class.java)
                        savingList.add(savingData!!)
                    }
                    val mAdaptor = savingAdaptor(savingList)
                    savingRecyclerView.adapter=mAdaptor

                    mAdaptor.setOnItemClickListner(object :savingAdaptor.onItemClickListner{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@feching3, Expenses::class.java)

                            //put extras
                            intent.putExtra("savingId",savingList[position].savingId)
                            intent.putExtra("savingType",savingList[position].savingType)
                            intent.putExtra("savingAmount",savingList[position].savingAmount)
                            intent.putExtra("savingDate",savingList[position].savingDate)
                            intent.putExtra("savingComment",savingList[position].savingComment)
                            startActivity(intent)
                        }
                    })

                    savingRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}