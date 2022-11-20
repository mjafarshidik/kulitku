package com.developer.kulitku.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developer.kulitku.databinding.ActivityRegisterBinding
import com.developer.kulitku.ui.home.HomeActivity


class RegisterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.apply {
            pager.adapter = ViewPagerAdapter(supportFragmentManager)
            tablayoutRegister.setupWithViewPager(binding.pager)
            btnBack.setOnClickListener {
                this@RegisterActivity.onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
        intent.putExtra("Check", 1)
        startActivity(intent)
    }
}