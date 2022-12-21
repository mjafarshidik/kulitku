package com.developer.kulitku.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.developer.kulitku.data.source.remote.SignUpBody
import com.developer.kulitku.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mFirebaseUser: FirebaseUser
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var uid: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        mAuth = Firebase.auth

        mAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mAuth.currentUser!!
        uid = mAuth.uid!!.toString()
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseInstance.getReference("kulitku")

        if (uid.isNotEmpty()) {
//            getUserData()
        }

    }

//    private fun getUserData() {
//        binding.apply {
//            mDatabaseReference.child(uid).addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val kulitku = snapshot.getValue(SignUpBody::class.java)
//                    edtName.setText(kulitku?.nama.toString())
//                    spGender.setText(kulitku?.jenis_kelamin.toString())
//                    spSkinType.setText(kulitku?.jenis_kulit.toString())
//                    edtBirthDate.setText(kulitku?.tanggal_lahir.toString())
//                    edtEmail.setText(kulitku?.email.toString())
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.d("Data", error.message)
//                }
//            })
//        }
//    }
}