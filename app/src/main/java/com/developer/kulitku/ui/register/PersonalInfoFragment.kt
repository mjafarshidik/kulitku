package com.developer.kulitku.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developer.kulitku.R
import com.developer.kulitku.databinding.FragmentPersonalInfoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class PersonalInfoFragment : Fragment() {
    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding
    //private val viewModel: RegisterViewModel by viewModels()

    private var listGender = arrayOf("Jenis Kelamin", "Pria", "Wanita")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)

        binding?.apply {
            buttonNext.setOnClickListener {
                val name = edittextName.text.toString()
                val email = edittextEmail.text.toString()
                val password = edittextPassword.text.toString()
                val birthdate = edittextBirthdate.text.toString()

                val bundle = Bundle()
                bundle.putString("NAME", name)
                bundle.putString("EMAIL", email)
                bundle.putString("BIRTHDATE", birthdate)
                bundle.putString("PASSWORD", password)

                val fragment = SkinInfoFragment()
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.frame_personal_info, fragment)?.commit()
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupSpinner()
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
}