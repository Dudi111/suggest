package com.example.autosuggestion.adapterViewHolder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autosuggestion.R
import com.example.autosuggestion.model.Address

class addAdatper(
    private val context: Context,
    private val locationList:MutableList<Address>):
 RecyclerView.Adapter<addViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addViewHolder {
     val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return addViewHolder(view)
    }

    override fun onBindViewHolder(holder: addViewHolder, position: Int) {
      val address=locationList[position]
        holder.setItem(address)
    }

    override fun getItemCount(): Int {
   return  locationList.size
    }
}