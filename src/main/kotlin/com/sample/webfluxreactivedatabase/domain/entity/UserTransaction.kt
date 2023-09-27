package com.sample.webfluxreactivedatabase.domain.entity

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

class UserTransaction(
    id: Int,
    userId: Int,
    amount: Double,
    transactionDate: LocalDateTime
) {

    @Id
    var id: Int
        private set
    var userId: Int
        private set
    var amount: Double
        private set
    var transactionDate: LocalDateTime
        private set

    init {
        this.id = id
        this.userId = userId
        this.amount = amount
        this.transactionDate = transactionDate
    }
}