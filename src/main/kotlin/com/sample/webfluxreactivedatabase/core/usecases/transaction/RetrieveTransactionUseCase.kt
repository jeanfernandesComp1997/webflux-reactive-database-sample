package com.sample.webfluxreactivedatabase.core.usecases.transaction

import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import reactor.core.publisher.Mono

interface RetrieveTransactionUseCase {

    fun execute(id: Int): Mono<TransactionDto>
}