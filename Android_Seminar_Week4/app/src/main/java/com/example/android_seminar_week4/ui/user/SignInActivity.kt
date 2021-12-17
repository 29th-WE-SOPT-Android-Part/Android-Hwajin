package com.example.android_seminar_week4.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android_seminar_week4.ui.MainActivity
import com.example.android_seminar_week4.data.service.ServiceCreator
import com.example.android_seminar_week4.data.request.RequestLoginData
import com.example.android_seminar_week4.data.response.ResponseLoginData
import com.example.android_seminar_week4.databinding.ActivitySignInBinding
import com.example.android_seminar_week4.util.shortToast
import org.sopt.myapplication.util.SoptSharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginButton()
        signInButton()
        isAutoLogin()
    }

    private fun loginButton(){
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text
            val pw = binding.etPw.text
            if(id.isEmpty() || pw.isEmpty()) {
                shortToast("아이디와 비밀번호를 모두 입력해주세요.")
            }else{
                initNetWork()
            }
        }

        binding.cbAutoLogin.setOnClickListener {
            SoptSharedPreference.setAutoLogin(this, binding.cbAutoLogin.isChecked)
        }
    }

    private fun initNetWork(){
        val requestLoginData = RequestLoginData(
            binding.etId.text.toString(),
            binding.etPw.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ){
                if(response.isSuccessful){
                    shortToast("${response.body()?.data?.email}님 반갑습니다.")
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }else{
                    shortToast("로그인에 실패하였습니다.")
                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable){
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

    private fun signInButton(){
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isAutoLogin(){
        if(SoptSharedPreference.getAutoLogin(this)){
            shortToast("자동로그인 완료")
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            finish()
        }
    }
}