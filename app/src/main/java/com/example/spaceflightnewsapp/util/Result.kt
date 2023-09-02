package com.example.spaceflightnewsapp.util

sealed class Result<out R> {

    data class Success<out T>(val data: T? = null) : Result<T>()

    data class Error(val error: Throwable) : Result<Nothing>()

    object Loading : Result<Nothing>()
}
