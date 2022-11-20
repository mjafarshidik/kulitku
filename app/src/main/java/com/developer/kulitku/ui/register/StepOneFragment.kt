package com.developer.kulitku.ui.register

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developer.kulitku.R
import com.developer.kulitku.databinding.FragmentStepOneBinding
import com.developer.kulitku.ui.home.HomeActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class StepOneFragment : Fragment() {
    private var _binding: FragmentStepOneBinding? = null
    private val binding get() = _binding
    private val viewModel: RegisterViewModel by viewModels()

    private var listGender = arrayOf("Jenis Kelamin", "Pria", "Wanita")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStepOneBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupSpinner()
        initObservable()
        binding?.apply {
            buttonNext.setOnClickListener {
                val name = edittextName.text.toString()
                val email = edittextEmail.text.toString()
                val password = edittextPassword.text.toString()
                val birthdate = edittextBirthdate.text.toString()

                val bundle = Bundle()
                bundle.putString(StepTwoFragment.EXTRA_NAME, name)
                bundle.putString(StepTwoFragment.EXTRA_EMAIL, email)
                bundle.putString(StepTwoFragment.EXTRA_BIRTHDATE, birthdate)
                bundle.putString(StepTwoFragment.EXTRA_PASSWORD, password)

                val fragment = StepTwoFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.frame_personal_info, fragment)?.commit()
            }
        }

        binding?.buttonNext?.setOnClickListener {
            val email = binding?.edittextEmail?.text.toString().trim()
            val password = binding?.edittextPassword?.text.toString().trim()
            viewModel.register(email, password)
            Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSpinner() {
        val spinnerStatus =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listGender)
        spinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerGender?.adapter = spinnerStatus
    }

    private fun setupView() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTheme(R.style.ThemeOverlay_App_DatePicker)
                .build()

        datePicker.addOnPositiveButtonClickListener {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = sdf.format(it)
            binding?.edittextBirthdate?.setText(date)
        }

        binding?.edittextBirthdate?.setOnClickListener {
            val isDatePickerExist =
                parentFragmentManager.findFragmentByTag("MATERIAL_DATE_PICKER") != null
            if (!isDatePickerExist) {
                datePicker.show(parentFragmentManager, "DATE_PICKER")
            }
        }
    }

    private fun initObservable(){
        viewModel.registerStatus.observe(requireActivity()) { success ->
            if (success == true) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Register failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}