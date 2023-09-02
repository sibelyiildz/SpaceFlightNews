package com.example.spaceflightnewsapp.util

sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()

    data class Failure(val error: Exception) : Resource<Nothing>()
}