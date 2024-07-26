package com.example.testapp.presentation

import com.example.testapp.domain.ExceptionType
import com.example.testapp.presentation.core.ResourceManager

class FakeResourceManager(private val exception: ExceptionType): ResourceManager {
    override fun fetchString(resId: Int): String {
        return if(exception == ExceptionType.GENERIC)
            "Что-то пошло не так"
        else if(exception == ExceptionType.NOTFOUND)
            "Не удалось загрузить продукт"
        else if(exception == ExceptionType.UNKNOWNHOST)
            "Не удалось подключиться к серверу"
        else ""
    }
}