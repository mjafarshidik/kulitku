package com.developer.kulitku.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.developer.kulitku.R
import com.developer.kulitku.databinding.ActivitySplashBinding
import com.developer.kulitku.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, 2000)

        supportActionBar?.hide()
    }
}