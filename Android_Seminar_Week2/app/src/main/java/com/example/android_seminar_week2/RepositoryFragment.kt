package com.example.android_seminar_week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_seminar_week2.databinding.FragmentFollowerBinding
import com.example.android_seminar_week2.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {
    private lateinit var repositoryAdapter : RepositoryAdapter
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter(){
        repositoryAdapter = RepositoryAdapter()

        binding.rvRepository.adapter = repositoryAdapter

        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("SOPT 과제 레포지토리", "29기 SOPT 안드로이드 과제"),
                RepositoryData("대학 과제 레포지토리", "대학교 실습 과제"),
                RepositoryData("안드로이드 프로젝트 레포지토리", "안드로이드 프로젝트"),
                RepositoryData("서버 프로젝트 레포지토리", "서버 프로젝트")
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}