package com.droidtechlab.yahooapi.data.network

sealed class Results<out T : Any?>

data class Success<out T : Any>(val data: T) : Results<T>()

data class Error<out T : Any?>(val data: T?) : Results<T>()

data class Failure(val throwable: Throwable) : Results<Nothing>()