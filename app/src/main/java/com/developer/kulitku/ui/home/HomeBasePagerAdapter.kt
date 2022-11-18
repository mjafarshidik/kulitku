package com.developer.kulitku.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.developer.kulitku.ui.home.fragment.PreSurveyFragment
import com.developer.kulitku.ui.home.fragment.ResultSkinDashboardFragment

class HomeBasePagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        PreSurveyFragment(),
        ResultSkinDashboardFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}