package com.example.android_seminar_week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_seminar_week3.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var sampleTabViewPagerAdapter : SampleTabViewPagerAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter(){
        val fragmentList = listOf(FollowingFragment(), HomeFollowerFragment())

        sampleTabViewPagerAdapter = SampleTabViewPagerAdapter(this)
        sampleTabViewPagerAdapter.fragmentList.addAll(fragmentList)

        binding.vpFollow.adapter = sampleTabViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLable = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) {tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}