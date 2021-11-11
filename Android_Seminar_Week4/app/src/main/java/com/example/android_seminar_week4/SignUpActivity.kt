package com.example.android_seminar_week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android_seminar_week4.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInButton()
    }

    private fun signInButton(){
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text
            val id = binding.etId.text
            val pw = binding.etPw.text
            if(name.isEmpty() || id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }else{
                finish()
            }
        }
    }
}