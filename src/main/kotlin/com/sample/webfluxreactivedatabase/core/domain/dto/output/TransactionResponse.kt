package com.sample.webfluxreactivedatabase.core.domain.dto.output

import com.sample.webfluxreactivedatabase.core.domain.enums.TransactionStatus

data class TransactionResponse(
    val id: Int,
    val userId: Int,
    val amount: Double,
    val status: TransactionStatus? = null
)
