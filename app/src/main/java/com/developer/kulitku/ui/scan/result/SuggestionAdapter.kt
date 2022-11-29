package com.developer.kulitku.ui.scan.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.kulitku.data.source.remote.SuggestionResponse
import com.developer.kulitku.databinding.ItemSuggestBinding
import java.util.ArrayList

class SuggestionAdapter : RecyclerView.Adapter<SuggestionAdapter.ListViewHolder>() {
    private val listSuggestion: ArrayList<SuggestionResponse> = arrayListOf()

    fun setSuggestion(Suggestion: List<SuggestionResponse>) {
        this.listSuggestion.clear()
        this.listSuggestion.addAll(Suggestion)
        this.notifyDataSetChanged()
    }

    class ListViewHolder(private val binding: ItemSuggestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SuggestionResponse) {
            with(binding) {
                textviewSuggest.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsRecipeBinding =
            ItemSuggestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsRecipeBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listSuggestion[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listSuggestion.size
    }
}