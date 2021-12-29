package com.example.android_seminar_week4.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_seminar_week4.R
import com.example.android_seminar_week4.databinding.ActivityHomeBinding
import com.example.android_seminar_week4.ui.home.follower.FollowerFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        initTransactionEvent()

        setContentView(binding.root)

    }

    private fun initTransactionEvent(){
        val fragmentFollower = FollowerFragment()
        val fragmentRepository = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_follower_repo, fragmentFollower).commit()

        binding.btnFollower.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.container_follower_repo, fragmentFollower).commit()
        }

        binding.btnRepository.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_follower_repo, fragmentRepository).commit()
        }
    }

}