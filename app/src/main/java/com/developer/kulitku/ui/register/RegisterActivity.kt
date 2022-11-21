package com.developer.kulitku.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developer.kulitku.databinding.ActivityRegisterBinding
import com.developer.kulitku.ui.auth.AuthActivity
import com.developer.kulitku.ui.home.HomeActivity


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            pager.adapter = ViewPagerAdapter(supportFragmentManager)
            tablayoutRegister.setupWithViewPager(binding.pager)
            buttonBack.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, AuthActivity::class.java))
            }
        }
    }
}