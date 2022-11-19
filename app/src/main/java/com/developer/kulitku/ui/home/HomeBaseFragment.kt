package com.developer.kulitku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulitku.data.source.remote.kubaca.KubacaData
import com.developer.kulitku.data.source.remote.kubaca.KubacaResponse
import com.developer.kulitku.data.source.remote.kulitku.KulitkuData
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.databinding.FragmentHomeBaseBinding
import com.developer.kulitku.ui.home.adapter.KubacaAdapter
import com.developer.kulitku.ui.home.adapter.KulitkuAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeBaseFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentHomeBaseBinding? = null
    private val binding get() = _binding!!
    private var listKubaca: ArrayList<KubacaResponse> = arrayListOf()
    private var listKulitku: ArrayList<KulitkuResponse> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = Firebase.auth

        binding.apply {
            vpHome.adapter = HomeBasePagerAdapter(requireActivity().supportFragmentManager)
            rvKubaca.setHasFixedSize(true)
            listKubaca.addAll(KubacaData.listData)
            listKulitku.addAll(KulitkuData.listData)
            showRecyclerList()
        }
    }

    private fun showRecyclerList() {
        binding.apply {
            rvKubaca.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvKulitku.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val kubacaAdapter = KubacaAdapter(listKubaca)
            val kulitkuAdapter = KulitkuAdapter(listKulitku)
            rvKubaca.adapter = kubacaAdapter
            rvKulitku.adapter = kulitkuAdapter
        }
    }
}