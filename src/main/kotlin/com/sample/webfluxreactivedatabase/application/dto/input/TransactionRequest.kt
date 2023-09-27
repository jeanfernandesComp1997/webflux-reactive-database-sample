package com.sample.webfluxreactivedatabase.application.dto.input

data class TransactionRequest(
    val userId: Int,
    val amount: Double
)