package com.sample.webfluxreactivedatabase.core.usecases.transaction

import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import reactor.core.publisher.Flux

interface RetrieveTransactionByUserUseCase {

    fun execute(userId: Int): Flux<UserTransaction>
}