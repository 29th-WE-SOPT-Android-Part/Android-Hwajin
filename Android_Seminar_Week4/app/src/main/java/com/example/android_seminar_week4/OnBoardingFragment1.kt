package com.example.android_seminar_week4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_seminar_week4.databinding.FragmentOnBoarding1Binding

class OnBoardingFragment1 : Fragment() {
    private var _binding: FragmentOnBoarding1Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding1Binding.inflate(layoutInflater, container, false)

        initNextBtnListener()

        return binding.root
    }

    private fun initNextBtnListener(){
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment1_to_onBoardingFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}