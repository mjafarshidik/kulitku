package com.developer.kulitku.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.developer.kulitku.R
import com.developer.kulitku.data.source.local.SharedPrefs
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import com.developer.kulitku.databinding.ActivitySplashBinding
import com.developer.kulitku.ui.home.HomeActivity
import com.google.android.material.slider.Slider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.orhanobut.hawk.Hawk

class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, 2000)
        checkSession()
    }

    private fun checkSession() {
        Hawk.init(baseContext).build()
        val signInData: SignInResponse? = Hawk.get(SharedPrefs.KEY_LOGIN)
        if (signInData == null) {
            Intent(this, SliderActivity::class.java)
            startActivity(intent)
        } else {
            Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}