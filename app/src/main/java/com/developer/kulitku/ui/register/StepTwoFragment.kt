package com.developer.kulitku.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.databinding.FragmentStepTwoBinding


class StepTwoFragment : Fragment() {
    private var _binding: FragmentStepTwoBinding? = null
    private val binding get() = _binding
    private val viewModel: RegisterViewModel by viewModels()

    private var siName = ""
    private var siEmail = ""
    private var siPassword = ""
    private var siConfirmPassword = ""
    private var siGender = ""
    private var siBirthdate = ""

    private var listDisease = arrayOf(
        "Berminyak",
        "Normal",
        "Kering",
        "Kombinasi",
        "Sensitif"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStepTwoBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner()
//        initObservable()
//        signup()

//        val args = this.arguments
//        siName = args?.getString(EXTRA_NAME).toString()
//        siEmail = args?.getString(EXTRA_EMAIL).toString()
//        siBirthdate = args?.getString(EXTRA_BIRTHDATE).toString()
//        siPassword = args?.getString(EXTRA_PASSWORD).toString()
//        siGender = "Pria"
//
//        Log.d("EK", siName)
    }

    private fun setupSpinner() {
        val spinnerStatus =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listDisease)
        spinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerSkin?.adapter = spinnerStatus
    }

    private fun initObservable() {
//        viewModel.registerStatus.observe(requireActivity()) {
//            when (it) {
//                is ResultState.Success -> {
//                    if (it.value) intentToLogin()
//                }
//                is ResultState.Failure -> {
//                    binding?.buttonSignup?.setText(com.developer.kulitku.R.string.daftar)
//                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//                is ResultState.Loading -> {
//                    binding?.buttonSignup?.setText(com.developer.kulitku.R.string.please_wait)
//                }
//            }
//        }
    }

    private fun signup() {
        binding?.apply {
//            buttonSignup.setOnClickListener {
//                viewModel.register(siName, siGender, siBirthdate, siEmail, siPassword)
//            }
        }
    }

    private fun intentToLogin() {
        Toast.makeText(requireContext(), "Berhasil Daftar", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_BIRTHDATE = "extra_birthdate"
        const val EXTRA_PASSWORD = "extra_password"
    }
}