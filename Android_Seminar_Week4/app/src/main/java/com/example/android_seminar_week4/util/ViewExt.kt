package com.example.android_seminar_week4.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.Px
import kotlin.math.roundToInt

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@Px
fun View.px(dp: Int) = (dp * resources.displayMetrics.density).roundToInt()