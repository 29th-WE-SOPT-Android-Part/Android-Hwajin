package com.example.android_seminar_week4

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SampleTabViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    val fragmentList = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}