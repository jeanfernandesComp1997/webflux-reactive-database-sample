package com.sample.webfluxreactivedatabase.application.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}