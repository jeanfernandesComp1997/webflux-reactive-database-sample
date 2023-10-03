package com.sample.webfluxreactivedatabase.application.mapper

import com.sample.webfluxreactivedatabase.domain.enums.TransactionStatus

interface Mapper<T, U> {

    fun map(t: T): U
}

interface CustomMapper<T, U> {

    fun map(t: T, status: TransactionStatus): U
}