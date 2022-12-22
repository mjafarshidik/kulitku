package com.developer.kulitku.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.developer.kulitku.R
import com.developer.kulitku.data.source.local.SharedPrefs
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import com.developer.kulitku.databinding.ActivityHomeBinding
import com.developer.kulitku.ui.auth.AuthActivity
import com.developer.kulitku.ui.login.LoginActivity
import com.developer.kulitku.ui.scan.AddPhotoActivity
import com.developer.kulitku.ui.splash.SliderActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.orhanobut.hawk.Hawk

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home_base,
            R.id.navigation_kubuku,
            R.id.navigation_kulitku,
            R.id.navigation_profile
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.background = null
        navView.menu.getItem(2).isEnabled = false

        binding.fabScan.setOnClickListener {
            val intent = Intent(this, AddPhotoActivity::class.java)
            startActivity(intent)
        }
        checkSession()
    }

    private fun checkSession() {
        val signInData: SignInResponse? = Hawk.get(SharedPrefs.KEY_LOGIN)
        Log.d("BUTU", signInData.toString())
        if (signInData == null) {
            Intent(this, SliderActivity::class.java)
            startActivity(intent)
        }
    }
}