package com.example.myapplication.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.BillModel

class billAdaptor(private val BillList: ArrayList<BillModel>):
    RecyclerView.Adapter<billAdaptor.ViewHolder>(){

    private lateinit var mlistner : onItemClickListner

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mlistner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.billdata, parent,false)
        return ViewHolder(itemView,mlistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBill = BillList[position]
        holder.tvBillName.text = currentBill.billType
    }

    override fun getItemCount(): Int {
        return BillList.size
    }
    class ViewHolder(itemView:View,clickListner:onItemClickListner) : RecyclerView.ViewHolder(itemView) {

        val tvBillName : TextView = itemView.findViewById(R.id.tvBillName)

        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}
