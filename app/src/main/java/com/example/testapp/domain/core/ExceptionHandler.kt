package com.example.testapp.domain.core

import android.content.res.Resources.NotFoundException
import java.lang.Exception
import java.net.UnknownHostException

interface ExceptionHandler {
    fun map(error: java.lang.Exception): Throwable

    class BaseExceptionHandler(): ExceptionHandler{
        override fun map(error: Exception): Throwable {
            return when(error){
                is UnknownHostException -> UnknownHost()
                is NotFoundException -> NotFound()
                else -> Generic()
            }
        }
    }
}