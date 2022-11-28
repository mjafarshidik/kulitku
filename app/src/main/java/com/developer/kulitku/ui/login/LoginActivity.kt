package com.developer.kulitku.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.developer.kulitku.databinding.ActivityLoginBinding
import com.developer.kulitku.ui.auth.AuthActivity
import com.developer.kulitku.ui.home.HomeActivity
import com.developer.kulitku.ui.register.RegisterActivity
import com.developer.kulitku.ui.reset_password.ResetPasswordActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = Firebase.auth

        initObservable()

        binding.buttonBack.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Email atau password tidak boleh kosong", Toast.LENGTH_SHORT)
                    .show()
            } else {
                signIn(email, password)
            }
        }

        binding.textviewSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    private fun initObservable() {
        viewModel.signInStatus.observe(this) { success ->
            if (success == true) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}