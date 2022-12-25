package com.developer.kulitku.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.developer.kulitku.data.source.local.SharedPrefs
import com.developer.kulitku.data.source.remote.SignUpBody
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import com.developer.kulitku.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.orhanobut.hawk.Hawk


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDatabaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        getUserData()
    }

    private fun getUserData() {
        binding.apply {
            val userData: SignInResponse? = Hawk.get(SharedPrefs.KEY_LOGIN)
            if (userData != null) {
                edtName.setText(userData.nama.toString())
                spGender.setText(userData.jenisKelamin.toString())
                spSkinType.setText(userData.jenisKulit.toString())
                edtBirthDate.setText(userData.tanggalLahir.toString())
                edtEmail.setText(userData.email.toString())
            }
        }
    }
}