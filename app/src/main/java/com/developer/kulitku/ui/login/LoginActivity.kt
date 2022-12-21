package com.developer.kulitku.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.developer.kulitku.R
import com.developer.kulitku.data.source.remote.ResultState
import com.developer.kulitku.databinding.ActivityLoginBinding
import com.developer.kulitku.ui.auth.AuthActivity
import com.developer.kulitku.ui.home.HomeActivity
import com.developer.kulitku.ui.register.RegisterActivity
import com.developer.kulitku.ui.reset_password.ResetPasswordActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var siEmail: String
    private lateinit var siPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initObservable()
        signIn()
        binding.textviewSignup.setOnClickListener {
            intentToSignUp()
        }
    }

    private fun initObservable() {
        viewModel.apply {
            emailError.observe(this@LoginActivity) {
                if (it.isNotEmpty()) {
                    binding.email.error = it
                    binding.email.requestFocus()
                } else {
                    binding.email.error = null
                }
            }

            passwordError.observe(this@LoginActivity) {
                if (it.isNotEmpty()) {
                    binding.password.error = it
                    binding.password.requestFocus()
                } else {
                    binding.password.error = null
                }
            }

            signInStatus.observe(this@LoginActivity) {
                when (it) {
                    is ResultState.Success -> {
                        if (it.value) pushLogin()
                    }
                    is ResultState.Failure -> {
                        binding.btnLogin.setText(R.string.signIn)
                        Toast.makeText(this@LoginActivity, it.throwable.message, Toast.LENGTH_SHORT).show()
                    }
                    is ResultState.Loading -> {
                        binding.btnLogin.setText(R.string.please_wait)
                    }
                }
            }
        }
    }

    private fun signIn() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (btnLogin.text != getString(R.string.please_wait)) {
                    siEmail = email.text.toString()
                    siPassword = password.text.toString()

                    viewModel.signIn(siEmail, siPassword)
                }
            }
        }
    }

    private fun intentToSignUp() {
        binding.apply {
            textviewSignup.setOnClickListener {
                val intentSignUp = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intentSignUp)
            }
        }
    }

    private fun pushLogin() {
        val intentSignIn = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intentSignIn)
    }
}