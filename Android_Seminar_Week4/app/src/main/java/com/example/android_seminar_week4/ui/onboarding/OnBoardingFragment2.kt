package com.example.android_seminar_week4.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_seminar_week4.R
import com.example.android_seminar_week4.databinding.FragmentOnBoarding2Binding

class OnBoardingFragment2 : Fragment() {
    private var _binding: FragmentOnBoarding2Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding2Binding.inflate(layoutInflater, container, false)

        initNextBtnListener()

        return binding.root
    }

    private fun initNextBtnListener(){
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment2_to_onBoardingFragment3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}