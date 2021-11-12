package com.example.android_seminar_week4

import com.google.gson.annotations.SerializedName

data class RequestLoginData(
    //@SerializedName("email") //이름이 다른 경우 사용
    val email : String,
    val password: String
)
