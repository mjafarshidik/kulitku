//package com.developer.kulitku.ui.home.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
//import com.developer.kulitku.R
//
//class KulitkuHomeAdapter () : RecyclerView.Adapter<KulitkuHomeAdapter.ListViewHolder>() {
//    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var tvTitle : TextView = itemView.findViewById(R.id.tvTitleKulitku)
//        var ivKulitku : ImageView = itemView.findViewById(R.id.ivKulitku)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): KulitkuHomeAdapter.ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_kulitku_home, parent, false)
//        return ListViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: KulitkuHomeAdapter.ListViewHolder, position: Int) {
//        val kubaca = listKulitku[position]
//        holder.tvTitle.text = kubaca.title
//        Glide.with(holder.itemView.context)
//            .load(kubaca.photo)
//            .apply(RequestOptions().override(160, 160))
//            .into(holder.ivKulitku)
//    }
//
//    override fun getItemCount(): Int {
//        return listKulitku.size
//    }
//}