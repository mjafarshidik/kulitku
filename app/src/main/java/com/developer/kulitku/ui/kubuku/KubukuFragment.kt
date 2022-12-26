package com.developer.kulitku.ui.kubuku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.databinding.FragmentKubukuBinding

class KubukuFragment : Fragment() {
    private var _binding: FragmentKubukuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: KubukuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKubukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllArticle()

        showRecyclerList()
        with(binding.rvKubuku) {
            this.layoutManager = GridLayoutManager(activity,2)
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun showRecyclerList() {
        binding.apply {
            viewModel.apply {
                getListArticle.observe(requireActivity()) {
                    when (it) {
                        is ResultState.Success -> {
                            val adapter = KubukuAdapter()
                            rvKubuku.adapter = adapter
                            adapter.setData(it.value)
                        }
                        is ResultState.Failure -> {
                            Toast.makeText(
                                requireActivity(),
                                it.throwable.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is ResultState.Loading -> {
                        }
                    }
                }
            }
        }
    }
}