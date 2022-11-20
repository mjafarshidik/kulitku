package com.developer.kulitku.ui.kubuku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.R
import com.developer.kulitku.data.source.remote.kubuku.KubukuResponse

class KubukuAdapter (private val listKubuku: ArrayList<KubukuResponse>) : RecyclerView.Adapter<KubukuAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle : TextView = itemView.findViewById(R.id.tvTitleDisease)
        var tvDesc : TextView = itemView.findViewById(R.id.tvDescDisease)
        var ivKulitku : ImageView = itemView.findViewById(R.id.ivImageKubuku)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kubuku, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val kubaca = listKubuku[position]
        holder.tvTitle.text = kubaca.title
        holder.tvDesc.text = kubaca.desc
        Glide.with(holder.itemView.context)
            .load(kubaca.photo)
            .apply(RequestOptions().override(160, 160))
            .into(holder.ivKulitku)
    }

    override fun getItemCount(): Int {
        return listKubuku.size
    }
}