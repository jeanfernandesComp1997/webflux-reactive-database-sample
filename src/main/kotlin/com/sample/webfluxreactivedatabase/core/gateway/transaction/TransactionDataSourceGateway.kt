package com.sample.webfluxreactivedatabase.core.gateway.transaction

import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TransactionDataSourceGateway {

    fun findByUserId(userId: Int): Flux<UserTransaction>
    fun save(transaction: UserTransaction): Mono<UserTransaction>
    fun findById(id: Int): Mono<UserTransaction>
}