package com.example.android_seminar_week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.android_seminar_week4.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initProfileImg()
        initTransactionEvent()

        return binding.root
    }

    private fun initProfileImg(){
        Glide.with(binding.root).load("https://cdn.pixabay.com/photo/2017/08/24/10/15/quokka-2676171_1280.jpg").circleCrop().into(binding.ivProfile)
    }

    private fun initTransactionEvent(){
        val fragmentFollower = FollowerFragment()
        val fragmentRepository = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.container_follower_repo, fragmentFollower).commit()
        binding.btnFollower.isSelected = true

        binding.btnFollower.setOnClickListener{
            childFragmentManager.beginTransaction()
                .replace(R.id.container_follower_repo, fragmentFollower)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            binding.btnFollower.isSelected = true
            binding.btnRepository.isSelected = false
        }

        binding.btnRepository.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.container_follower_repo, fragmentRepository)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
            binding.btnFollower.isSelected = false
            binding.btnRepository.isSelected = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}