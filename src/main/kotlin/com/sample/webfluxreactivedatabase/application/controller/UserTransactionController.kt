package com.sample.webfluxreactivedatabase.application.controller

import com.sample.webfluxreactivedatabase.application.api.transaction.TransactionApi
import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.core.domain.dto.output.TransactionResponse
import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import com.sample.webfluxreactivedatabase.core.usecases.transaction.CreateTransactionUseCase
import com.sample.webfluxreactivedatabase.core.usecases.transaction.RetrieveTransactionByUserUseCase
import com.sample.webfluxreactivedatabase.core.usecases.transaction.RetrieveTransactionUseCase
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class UserTransactionController(
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val retrieveTransactionByUserUseCase: RetrieveTransactionByUserUseCase,
    private val retrieveTransactionUseCase: RetrieveTransactionUseCase
) : TransactionApi {

    override fun createTransaction(request: Mono<TransactionDto>): Mono<TransactionResponse> {
        return request
            .flatMap { transactionRequest ->
                createTransactionUseCase.execute(transactionRequest)
            }
    }

    override fun retrieveTransactionById(id: Int): Mono<TransactionDto> {
        return retrieveTransactionUseCase.execute(id)
    }

    override fun retrieveByUserId(userId: Int): Flux<UserTransaction> {
        return retrieveTransactionByUserUseCase.execute(userId)
    }
}