package com.example.testapp.domain.core

interface Mapper<S, R: Mapper2> {
    fun map(mapper: R): S



}

interface Mapper2{
}