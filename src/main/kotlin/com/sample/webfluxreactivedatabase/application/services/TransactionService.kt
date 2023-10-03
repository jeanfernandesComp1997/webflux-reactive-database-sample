package com.sample.webfluxreactivedatabase.application.services

import com.sample.webfluxreactivedatabase.application.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.application.dto.output.TransactionResponse
import reactor.core.publisher.Mono

interface TransactionService {

    fun retrieveById(id: Int): Mono<TransactionDto>
    fun createTransaction(transactionRequest: TransactionDto): Mono<TransactionResponse>
}