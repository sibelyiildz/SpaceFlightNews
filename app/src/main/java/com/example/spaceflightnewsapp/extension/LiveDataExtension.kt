package com.example.spaceflightnewsapp.extension

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}

fun <T> MutableLiveData<T>.setThreadingValue(data: T) {
    if (Thread.currentThread() == Looper.getMainLooper().thread) {
        this.value = data
    } else {
        this.postValue(data)
    }
}
