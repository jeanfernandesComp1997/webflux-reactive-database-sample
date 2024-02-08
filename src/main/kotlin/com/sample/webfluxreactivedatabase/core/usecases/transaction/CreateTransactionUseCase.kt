package com.sample.webfluxreactivedatabase.core.usecases.transaction

import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.core.domain.dto.output.TransactionResponse
import reactor.core.publisher.Mono

interface CreateTransactionUseCase {

    fun execute(transactionRequest: TransactionDto): Mono<TransactionResponse>
}