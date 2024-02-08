package com.sample.webfluxreactivedatabase.core.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "users")
class User(
    id: Int,
    name: String,
    balance: Double
) {

    @Id
    var id: Int
        private set

    var name: String
        private set

    var balance: Double
        private set

    init {
        this.id = id
        this.name = name
        this.balance = balance
    }
}