package com.developer.kulitku.ui.reset_password

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.developer.kulitku.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel: ResetPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonSend.setOnClickListener {
            val email = binding.edittextEmail.text.toString()
            resetPassword(email)
        }

        initObservable()
    }

    private fun resetPassword(email: String) {
        viewModel.resetPassword(email)
    }

    private fun initObservable() {
        viewModel.resetStatus.observe(this) { success ->
            if (success == true) {
                Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}