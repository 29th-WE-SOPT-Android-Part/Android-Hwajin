package com.example.android_seminar_week4.data.service

import com.example.android_seminar_week4.data.request.RequestLoginData
import com.example.android_seminar_week4.data.request.RequestSignUpData
import com.example.android_seminar_week4.data.response.ResponseLoginData
import com.example.android_seminar_week4.data.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SampleService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun postLogin(
        @Body requestLoginData : RequestLoginData
    ) : Call<ResponseLoginData>

    @Headers("Content-Type: application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body requestSignUpData : RequestSignUpData
    ) : Call<ResponseSignUpData>
}