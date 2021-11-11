package com.example.android_seminar_week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.android_seminar_week4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var sampleViewPagerAdapter: SampleViewPagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initAdapter()
        initBottomNavi()

        setContentView(binding.root)
    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())

        sampleViewPagerAdapter = SampleViewPagerAdapter(this)
        sampleViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpSample.adapter = sampleViewPagerAdapter
    }


    private fun initBottomNavi(){
        binding.vpSample.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvSample.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvSample.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.vpSample.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpSample.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpSample.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}