package com.sample.webfluxreactivedatabase.application.dto.models

data class TransactionDto(
    val id: Int,
    val userId: Int,
    val amount: Double
)