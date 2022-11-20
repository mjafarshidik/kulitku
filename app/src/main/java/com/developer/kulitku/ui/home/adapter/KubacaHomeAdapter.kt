package com.developer.kulitku.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developer.kulitku.R
import com.developer.kulitku.data.source.remote.kubaca.KubacaResponse

class KubacaHomeAdapter (private val listKubaca: ArrayList<KubacaResponse>) : RecyclerView.Adapter<KubacaHomeAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        var tvDate  : TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kubaca, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val kubaca = listKubaca[position]
        holder.tvTitle.text = kubaca.title
        holder.tvDate.text = kubaca.date
    }

    override fun getItemCount(): Int {
        return listKubaca.size
    }
}