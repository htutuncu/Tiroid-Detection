package com.example.tiroid

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val context: Context, var mList : MutableList<Result>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_result,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        //mList = mList.asReversed()
        val newList = mList.reversed()
        holder.bindItems(newList.get(position))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         var tvTst : TextView
         var tvTsh : TextView
         var tvT3 : TextView
         var tvMadtsh : TextView
         var result : TextView
         var cv : CardView

         init {
             tvTst = itemView.findViewById(R.id.tv_tst)
             tvTsh = itemView.findViewById(R.id.tv_tsh)
             tvT3 = itemView.findViewById(R.id.tv_t3)
             tvMadtsh = itemView.findViewById(R.id.tv_madtsh)
             result = itemView.findViewById(R.id.tv_result)
             cv = itemView.findViewById(R.id.cardView)
         }

        fun bindItems(item:Result){
            val mtst = "TST : " + item.tst.toString()
            val mtsh = "TSH : " + item.tsh.toString()
            val mt3 = "T3 : " + item.t3.toString()
            val mmadtsh = "MADTSH : " + item.madtsh.toString()
            tvTst.setText(mtst)
            tvTsh.setText(mtsh)
            tvT3.setText(mt3)
            tvMadtsh.setText(mmadtsh)
            result.setText(item.result)
            if(item.result == "Normal")
                result.setTextColor(Color.BLUE)
            else if(item.result == "Hypo")
                result.setTextColor(Color.RED)
            else if(item.result == "Hyper")
                result.setTextColor(Color.GREEN)

        }
    }

}