package com.example.testapp.presentation.core

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {
    fun fetchString(@StringRes resId: Int): String

    class Base(private val context: Context): ResourceManager {
        override fun fetchString(resId: Int): String {
            return context.getString(resId)
        }
    }
}