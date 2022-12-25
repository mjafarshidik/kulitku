package com.developer.kulitku.ui.kulitku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.databinding.ItemKulitkuBinding

class KulitkuAdapter () : RecyclerView.Adapter<KulitkuAdapter.ListViewHolder>() {
    private val listHistories: ArrayList<KulitkuResponse> = arrayListOf()

    fun setRecipes(histories: List<KulitkuResponse>) {
        this.listHistories.clear()
        this.listHistories.addAll(histories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsHistoriesBinding =
            ItemKulitkuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsHistoriesBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val items = listHistories[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return listHistories.size
    }

    class ListViewHolder(private val binding: ItemKulitkuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kulitku: KulitkuResponse) {
            with(binding) {
                tvResultTitle.text = "Hasil : " + kulitku.namaPenyakit
                tvDateResult.text = kulitku.createdAt

                Glide.with(itemView.context)
                    .load(kulitku.urlImage)
                    .apply(RequestOptions().override(160, 160))
                    .into(ivImageResult)
            }
        }
    }
}