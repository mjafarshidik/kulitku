package com.developer.kulitku.ui.kulitku

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulitku.data.source.local.SharedPrefs
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import com.developer.kulitku.databinding.FragmentKulitkuBinding
import com.orhanobut.hawk.Hawk

class KulitkuFragment : Fragment() {
    private var _binding: FragmentKulitkuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: KulitkuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKulitkuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData: SignInResponse? = Hawk.get(SharedPrefs.KEY_LOGIN)

        viewModel.getHistoryScan(userData?.userId!!)

        showRecyclerList()
        with(binding.rvKulitku) {
            this.layoutManager = LinearLayoutManager(activity)
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun showRecyclerList() {
        binding.apply {
            viewModel.apply {
                dataHistory.observe(requireActivity()) {
                    when (it) {
                        is ResultState.Success -> {
                            val adapter = KulitkuAdapter()
                            rvKulitku.adapter = adapter
                            adapter.setRecipes(it.value)
                            Log.d("KOKO", it.value.toString())
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