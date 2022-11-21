package com.developer.kulitku.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulitku.R
import com.developer.kulitku.data.source.remote.kubaca.KubacaData
import com.developer.kulitku.data.source.remote.kubaca.KubacaResponse
import com.developer.kulitku.data.source.remote.kulitku.KulitkuData
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.databinding.FragmentHomeBaseBinding
import com.developer.kulitku.ui.home.adapter.KubacaHomeAdapter
import com.developer.kulitku.ui.home.adapter.KulitkuHomeAdapter
import com.developer.kulitku.ui.splash.SliderActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class HomeBaseFragment : Fragment() {
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

        binding.apply {
            vpHome.adapter = HomeBasePagerAdapter(requireActivity().supportFragmentManager)
            rvKubaca.setHasFixedSize(true)
            listKubaca.addAll(KubacaData.listData)
            listKulitku.addAll(KulitkuData.listData)
            showRecyclerList()
            navigateToKubaca()
            navigateToKutulis()
            notification()
        }

        checkSession()

        val user = Firebase.auth.currentUser
        val username = user?.email
        binding.textviewUsername.text = username?.replace("@gmail.com", "")

        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd LLLL yyyy")
        val currentDate = sdf.format(date)
        binding.textviewDate.text = currentDate
    }

    private fun notification() {
        binding.apply {
            btnNotification.setOnClickListener {

            }
        }
    }

    private fun navigateToKutulis() {
        binding.apply {
            btnKulitkuMore.setOnClickListener {
                Navigation.createNavigateOnClickListener(R.id.action_homeBaseFragment_to_kulitkuFragment)
            }
        }
    }

    private fun navigateToKubaca() {
        binding.apply {
            btnMoreKubaca.setOnClickListener {
                Navigation.createNavigateOnClickListener(R.id.action_homeBaseFragment_to_kubukuFragment)
            }
        }
    }

    private fun showRecyclerList() {
        binding.apply {
            rvKubaca.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rvKulitku.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val kubacaAdapter = KubacaHomeAdapter(listKubaca)
            val kulitkuAdapter = KulitkuHomeAdapter(listKulitku)
            rvKubaca.adapter = kubacaAdapter
            rvKulitku.adapter = kulitkuAdapter
        }
    }

    private fun checkSession() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            val intent = Intent(requireContext(), SliderActivity::class.java)
            startActivity(intent)
        }
    }
}