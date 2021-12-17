package com.example.android_seminar_week4.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_seminar_week4.R
import com.example.android_seminar_week4.ui.user.SignInActivity
import org.sopt.myapplication.util.SoptSharedPreference

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAutoLogin()
        setContentView(R.layout.activity_on_boarding)
    }

    private fun isAutoLogin(){
        if(SoptSharedPreference.getAutoLogin(this)){
            startActivity(Intent(this@OnBoardingActivity, SignInActivity::class.java))
            finish()
        }
    }
}