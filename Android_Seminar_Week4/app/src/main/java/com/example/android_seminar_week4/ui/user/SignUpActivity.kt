package com.example.android_seminar_week4.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android_seminar_week4.data.service.ServiceCreator
import com.example.android_seminar_week4.data.request.RequestSignUpData
import com.example.android_seminar_week4.data.response.ResponseSignUpData
import com.example.android_seminar_week4.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                initNetwork()
            }
        }
    }

    private fun initNetwork(){
        val requestSignUpData = RequestSignUpData(
            binding.etName.text.toString(),
            binding.etId.text.toString(),
            binding.etPw.text.toString()
        )

        val call: Call<ResponseSignUpData> = ServiceCreator.sampleService.postSignUp(requestSignUpData)

        call.enqueue(object: Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@SignUpActivity, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}