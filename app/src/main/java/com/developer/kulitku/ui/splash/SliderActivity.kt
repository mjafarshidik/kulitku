package com.developer.kulitku.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.developer.kulitku.R
import com.developer.kulitku.databinding.ActivitySliderBinding
import com.developer.kulitku.ui.auth.AuthActivity
import com.developer.kulitku.ui.home.HomeActivity

class SliderActivity : AppCompatActivity() {
    private var binding: ActivitySliderBinding? = null
    private val fragmentList = ArrayList<androidx.fragment.app.Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(binding?.root)

        val adapter = SliderAdapter(this)
        val vpIntroSlider : ViewPager2 = findViewById(R.id.vpIntroSlider)
        val indicatorLayout : IndicatorLayout = findViewById(R.id.indicatorLayout)

        vpIntroSlider.adapter = adapter
        fragmentList.addAll(listOf(
            ZeroIntroFragment(), OneIntroFragment(), TwoIntroFragment(), ThreeIntroFragment(), FourIntroFragment()
        ))
        adapter.setFragmentList(fragmentList)
        indicatorLayout.setIndicatorCount(adapter.itemCount)
        indicatorLayout.selectCurrentPosition(0)
        registerListeners()

        supportActionBar?.hide()
    }

    private fun registerListeners() {
        val vpIntroSlider : ViewPager2 = findViewById(R.id.vpIntroSlider)
        val indicatorLayout : IndicatorLayout = findViewById(R.id.indicatorLayout)
        val tvSkip : TextView = findViewById(R.id.tvSkip)
        val tvNext : ImageView = findViewById(R.id.tvNext)

        vpIntroSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicatorLayout.selectCurrentPosition(position)
                if (position < fragmentList.lastIndex) {
                    tvSkip.visibility = View.VISIBLE
                } else {
                    tvSkip.visibility = View.GONE
                }
            }
        })
        tvSkip.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
        tvNext.setOnClickListener {
            val position = vpIntroSlider.currentItem
            if (position < fragmentList.lastIndex) {
                vpIntroSlider.currentItem = position + 1
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }
    }
}