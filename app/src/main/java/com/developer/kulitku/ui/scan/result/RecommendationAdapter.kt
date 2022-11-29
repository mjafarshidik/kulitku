package com.developer.kulitku.ui.scan.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.kulitku.data.source.remote.RecommendationIngredientResponse
import com.developer.kulitku.databinding.ItemRecommendationBinding
import java.util.ArrayList

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.ListViewHolder>() {
    private val listRecommendation: ArrayList<RecommendationIngredientResponse> = arrayListOf()

    fun setRecommendation(recommendation: List<RecommendationIngredientResponse>) {
        this.listRecommendation.clear()
        this.listRecommendation.addAll(recommendation)
        this.notifyDataSetChanged()
    }

    class ListViewHolder(private val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecommendationIngredientResponse) {
            with(binding) {
                textviewIngredientName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsRecipeBinding =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsRecipeBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listRecommendation[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listRecommendation.size
    }
}