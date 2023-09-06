package com.example.spaceflightnewsapp.util

import android.os.CountDownTimer

inline fun countDownTimer(
    millisInFuture: Long,
    countDownInterval: Long,
    crossinline onTick: (Long) -> Unit,
    crossinline onFinish: () -> Unit
): CountDownTimer {
    return object : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onTick(p0: Long) {
            onTick(p0)
        }

        override fun onFinish() {
            onFinish()
        }
    }
}