package com.developer.kulitku.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.developer.kulitku.R
import com.developer.kulitku.databinding.FragmentDialogProfileBinding
import com.developer.kulitku.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DialogProfileFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentDialogProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        mAuth = Firebase.auth
        btnToProfile()
        btnExit()
    }

    private fun btnExit() {
        binding.apply {
            btnExit.setOnClickListener {
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun btnToProfile() {
        binding.apply {
            btnProfile.setOnClickListener {
                Navigation.createNavigateOnClickListener(R.id.action_dialogProfileFragment_to_profileFragment)
            }
        }
    }
}