package com.example.sprint_2.domain.common

sealed class ResponseState {
    class Error(val error: String): ResponseState()
    class Success<T>(val data: T): ResponseState()
    class Loading(): ResponseState()
}