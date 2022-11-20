package com.developer.kulitku.ui.kulitku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulitku.data.source.remote.kulitku.KulitkuData
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.databinding.FragmentKulitkuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class KulitkuFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentKulitkuBinding? = null
    private val binding get() = _binding!!
    private var listKulitku: ArrayList<KulitkuResponse> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKulitkuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = Firebase.auth

        binding.apply {
            rvKulitku.setHasFixedSize(true)
            listKulitku.addAll(KulitkuData.listData)
            showRecyclerList()
        }
    }

    private fun showRecyclerList() {
        binding.apply {
            rvKulitku.layoutManager =
                LinearLayoutManager(activity)
            val kulitkuAdapter = KulitkuAdapter(listKulitku)
            rvKulitku.adapter = kulitkuAdapter
        }
    }
}