package com.developer.kulitku.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.developer.kulitku.R
import com.developer.kulitku.databinding.ActivitySplashBinding
import com.developer.kulitku.ui.home.HomeActivity
import com.google.android.material.slider.Slider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, SliderActivity::class.java))
            finish()
        }, 2000)

//        checkSession()
        auth = Firebase.auth
    }

    private fun checkSession() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val intent = Intent(this, SliderActivity::class.java)
        startActivity(intent)
    }
//
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            recreate()
//        }
//    }
}