package com.example.android_seminar_week4.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_seminar_week4.ui.user.SignInActivity
import com.example.android_seminar_week4.databinding.FragmentOnBoarding3Binding

class OnBoardingFragment3 : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)

        initNextBtnListener()

        return binding.root
    }

    private fun initNextBtnListener(){
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}