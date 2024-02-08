package com.sample.webfluxreactivedatabase.shared.mapper

import com.sample.webfluxreactivedatabase.core.domain.enums.TransactionStatus

interface Mapper<T, U> {

    fun map(t: T): U
}

interface CustomMapper<T, U> {

    fun map(t: T, status: TransactionStatus): U
}