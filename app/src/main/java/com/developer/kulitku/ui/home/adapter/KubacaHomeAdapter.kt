package com.developer.kulitku.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.R
import com.developer.kulitku.data.source.remote.kubaca.KubacaResponse
import com.developer.kulitku.data.source.remote.kubuku.KubukuResponse
import com.developer.kulitku.databinding.ItemKubacaBinding
import com.developer.kulitku.databinding.ItemKubukuBinding
import com.developer.kulitku.databinding.ItemKubukuHomeBinding
import com.developer.kulitku.ui.kubuku.KubukuAdapter
import java.text.SimpleDateFormat

class KubacaHomeAdapter() : RecyclerView.Adapter<KubacaHomeAdapter.ListViewHolder>() {
    private val listArticle: ArrayList<KubukuResponse> = arrayListOf()

    fun setData(histories: List<KubukuResponse>) {
        this.listArticle.clear()
        this.listArticle.addAll(histories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsKubukuHomeBinding =
            ItemKubacaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsKubukuHomeBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val items = listArticle[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    class ListViewHolder(private val binding: ItemKubacaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kubuku: KubukuResponse) {
            val inputDate = kubuku.createdAt
            val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val outputDate = inputDateFormat.parse(inputDate)
            val outputDateFormat = SimpleDateFormat("EEEE, dd LLLL yyyy")
            val result : String = outputDateFormat.format(outputDate)
            with(binding) {
                tvTitle.text = kubuku.title
                tvDate.text = result

                Glide.with(itemView.context)
                    .load(kubuku.urlImage)
                    .transform(GranularRoundedCorners (50F, 50F, 0F, 0F))
                    .apply(RequestOptions().override(500, 200))
                    .into(imageArticle)
            }
        }
    }
}