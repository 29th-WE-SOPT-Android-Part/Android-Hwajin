package com.example.android_seminar_week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_seminar_week3.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        initTransactionEvent()

        setContentView(binding.root)

        Toast.makeText(this, "정화진님 환영합니다", Toast.LENGTH_SHORT).show()

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