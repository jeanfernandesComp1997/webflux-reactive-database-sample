package com.sample.webfluxreactivedatabase.core.domain.dto.models

data class TransactionDto(
    val id: Int,
    val userId: Int,
    val amount: Double
)