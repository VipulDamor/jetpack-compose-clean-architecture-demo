package com.one993.jetpackcomposecleanarchitecture.core

sealed interface ResultState<out R> {
    data class Success<out T>(val data: T) : ResultState<T>
    data class Error(val message: String) : ResultState<Nothing>
}