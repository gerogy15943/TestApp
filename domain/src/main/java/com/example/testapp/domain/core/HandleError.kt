package com.example.testapp.domain.core

interface HandleError<S: Any, R: Any> {
    fun handle(source: S): R
}