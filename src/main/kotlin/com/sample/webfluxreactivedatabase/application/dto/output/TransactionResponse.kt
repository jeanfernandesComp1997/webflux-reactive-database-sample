package com.sample.webfluxreactivedatabase.application.dto.output

import com.sample.webfluxreactivedatabase.domain.enums.TransactionStatus

data class TransactionResponse(
    val id: Int,
    val userId: Int,
    val amount: Double,
    val status: TransactionStatus? = null
)
