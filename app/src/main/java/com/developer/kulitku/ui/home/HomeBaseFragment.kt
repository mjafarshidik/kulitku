package com.developer.kulitku.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.kulitku.R
import com.developer.kulitku.data.source.local.SharedPrefs
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.data.source.remote.kubaca.KubacaData
import com.developer.kulitku.data.source.remote.kubaca.KubacaResponse
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import com.developer.kulitku.databinding.FragmentHomeBaseBinding
import com.developer.kulitku.ui.home.adapter.KubacaHomeAdapter
import com.developer.kulitku.ui.kubuku.KubukuAdapter
import com.developer.kulitku.ui.kubuku.KubukuViewModel
import com.developer.kulitku.ui.splash.SliderActivity
import com.orhanobut.hawk.Hawk
import java.text.SimpleDateFormat
import java.util.*

class HomeBaseFragment : Fragment() {
    private var _binding: FragmentHomeBaseBinding? = null
    private val binding get() = _binding!!
    private var listKubaca: ArrayList<KubacaResponse> = arrayListOf()
    private val kubukuViewModel: KubukuViewModel by viewModels()

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
            rvKubuku.setHasFixedSize(true)

            //kubuku get data and show
            kubukuViewModel.getAllArticle()
            showRecyclerListKubuku()
            with(binding.rvKubuku) {
                this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                this.setHasFixedSize(true)
                this.adapter = adapter
            }

            showRecyclerList()
            navigateToKubaca()
            navigateToKutulis()
            notification()
        }

        checkSession()

        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd LLLL yyyy")
        val currentDate = sdf.format(date)
        binding.textviewDate.text = currentDate
    }

    private fun notification() {
        binding.apply {
            btnNotification.setOnClickListener {
                val builder = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog).create()
                val view = layoutInflater.inflate(R.layout.notification_dialog, null)
                val button = view.findViewById<Button>(R.id.btnClose)
                builder.setView(view)
                button.setOnClickListener {
                    builder.dismiss()
                }
                builder.setCanceledOnTouchOutside(false)
                builder.show()
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
            rvKulitku.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showRecyclerListKubuku() {
        binding.apply {
            kubukuViewModel.apply {
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

    private fun checkSession() {
        val loginData : SignInResponse? = Hawk.get(SharedPrefs.KEY_LOGIN)
        if (loginData == null) {
            val intent = Intent(requireContext(), SliderActivity::class.java)
            startActivity(intent)
        }
        val username = loginData?.email
        binding.textviewUsername.text = username?.replace("@gmail.com", "")
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            vpHome.adapter = HomeBasePagerAdapter(requireActivity().supportFragmentManager)
        }
    }
}