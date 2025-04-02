package com.example.starterproject.data.news

import retrofit2.HttpException
import retrofit2.Response

sealed class Result<T : Any> {
    class Success<T: Any>(val data: T) : Result<T>()
    class Error<T: Any>(val code: Int, val message: String?) : Result<T>()
    class Exception<T: Any>(val e: Throwable) : Result<T>()
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): Result<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Result.Success(body)
        } else {
            Result.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Result.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Result.Exception(e)
    }
}