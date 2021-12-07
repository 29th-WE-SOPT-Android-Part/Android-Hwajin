package com.example.android_seminar_week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_seminar_week3.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter(){
        followerAdapter = FollowerAdapter()

        binding.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_1280.jpg","정화진", "29기 안드로이드 파트원"),
                FollowerData("https://cdn.pixabay.com/photo/2016/03/28/10/05/kitten-1285341_1280.jpg","동그라미", "28기 안드로이드 파트원"),
                FollowerData("https://cdn.pixabay.com/photo/2020/06/30/22/34/dog-5357794_1280.jpg","세모", "29기 서버 파트원"),
                FollowerData("https://cdn.pixabay.com/photo/2017/07/25/01/22/cat-2536662_1280.jpg","네모", "29기 디자인 파트원")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}