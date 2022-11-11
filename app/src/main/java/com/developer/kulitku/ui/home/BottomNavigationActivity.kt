package com.developer.kulitku.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.developer.kulitku.R
import com.developer.kulitku.databinding.ActivityBottomNavigationBinding
import com.developer.kulitku.ui.scan.AddPhotoActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BottomNavigationActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scan : FloatingActionButton = findViewById(R.id.scan_button)
        scan.setOnClickListener(this)

        val navView: BottomNavigationView = binding.navDashboard

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_nav)
        navView.setupWithNavController(navController)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.scan_button -> {
                val intent = Intent(this, AddPhotoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}