package com.example.myapplication.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.incomeModel

class incomeAdaptor(private val IncomeList: ArrayList<incomeModel>):
    RecyclerView.Adapter<incomeAdaptor.ViewHolder>(){

    private lateinit var mlistner : onItemClickListner

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mlistner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.income_data, parent,false)
        return ViewHolder(itemView,mlistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentIncome = IncomeList[position]
        holder.tvIncomeName.text = currentIncome.incomeType
    }

    override fun getItemCount(): Int {
        return IncomeList.size
    }
    class ViewHolder(itemView:View,clickListner:onItemClickListner) : RecyclerView.ViewHolder(itemView) {

        val tvIncomeName : TextView = itemView.findViewById(R.id.tvIncomeName)

        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}
