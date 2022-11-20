package com.developer.kulitku.ui.kubuku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulitku.data.source.remote.kubuku.KubukuData
import com.developer.kulitku.data.source.remote.kubuku.KubukuResponse
import com.developer.kulitku.databinding.FragmentKubukuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class KubukuFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentKubukuBinding? = null
    private val binding get() = _binding!!
    private var listKubuku: ArrayList<KubukuResponse> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKubukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = Firebase.auth

        binding.apply {
            rvKubuku.setHasFixedSize(true)
            listKubuku.addAll(KubukuData.listData)
            showRecyclerList()
        }
    }

    private fun showRecyclerList() {
        binding.apply {
            rvKubuku.layoutManager =
                LinearLayoutManager(activity)
            val kubukuAdapter = KubukuAdapter(listKubuku)
            rvKubuku.adapter = kubukuAdapter
        }
    }
}