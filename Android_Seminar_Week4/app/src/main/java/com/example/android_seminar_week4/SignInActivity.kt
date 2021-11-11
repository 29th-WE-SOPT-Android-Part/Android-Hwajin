package com.example.android_seminar_week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_seminar_week4.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginButton()
        signInButton()
    }

    private fun loginButton(){
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text
            val pw = binding.etPw.text
            if(id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signInButton(){
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}