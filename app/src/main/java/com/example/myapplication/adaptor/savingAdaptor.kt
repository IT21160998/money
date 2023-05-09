package com.example.myapplication.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.savingModel


class savingAdaptor (
    private val SavingList: ArrayList<savingModel>):
    RecyclerView.Adapter<savingAdaptor.ViewHolder>(){

    private lateinit var mlistner : onItemClickListner

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListner: onItemClickListner){
        mlistner = clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.saving_data, parent,false)
        return ViewHolder(itemView,mlistner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSaving = SavingList[position]
        holder.tvSavingName.text = currentSaving.savingType
    }

    override fun getItemCount(): Int {
        return SavingList.size
    }
    class ViewHolder(itemView:View,clickListner:onItemClickListner) : RecyclerView.ViewHolder(itemView) {

        val tvSavingName : TextView = itemView.findViewById(R.id.tvSavingName)

        init {
            itemView.setOnClickListener{
                clickListner.onItemClick(adapterPosition)
            }
        }
    }

}

