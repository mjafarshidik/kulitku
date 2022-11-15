package com.developer.kulitku.ui.register

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> PersonalInfoFragment()
            1 -> SkinInfoFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Informasi Personal"
            }
            1 -> {
                return "Informasi Kulitmu"
            }
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int = 2
}