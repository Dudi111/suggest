package com.example.autosuggestion.adapterViewHolder

import android.view.View
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.autosuggestion.model.Address
import kotlinx.android.synthetic.main.item_layout.view.*
import java.net.Inet4Address

class addViewHolder(private val itemView: View):RecyclerView.ViewHolder(itemView) {
fun setItem(address: Address){
    itemView.apply {
        tvAddress.text=address.addressString
    }
}
}