package com.example.testapp.core

interface Mapper<S,R> {
    fun map(mapper: S): R
}