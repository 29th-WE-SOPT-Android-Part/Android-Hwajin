package com.example.android_seminar_week4.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_seminar_week4.databinding.ActivitySettingBinding
import org.sopt.myapplication.util.SoptSharedPreference

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDisabledAutoLogin()
    }

    private fun initDisabledAutoLogin(){
        binding.clAutoLogin.setOnClickListener {
            SoptSharedPreference.removeAutoLogin(this)
            Toast.makeText(this, "자동 로그인 해제", Toast.LENGTH_SHORT).show()
        }
    }
}