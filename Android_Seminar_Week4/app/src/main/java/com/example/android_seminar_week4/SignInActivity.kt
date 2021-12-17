package com.example.android_seminar_week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.android_seminar_week4.databinding.ActivitySignInBinding
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
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
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
                    //val data = response.body()?.data

                    Toast.makeText(this@SignInActivity, "${response.body()?.data?.email}님 반갑습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }else{
                    Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()

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
        }
    }

    private fun isAutoLogin(){
        if(SoptSharedPreference.getAutoLogin(this)){
            Toast.makeText(this, "자동 로그인 완료", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            finish()
        }
    }
}