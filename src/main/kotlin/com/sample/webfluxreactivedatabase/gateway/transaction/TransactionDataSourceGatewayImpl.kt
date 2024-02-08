package com.sample.webfluxreactivedatabase.gateway.transaction

import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import com.sample.webfluxreactivedatabase.core.gateway.transaction.TransactionDataSourceGateway
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TransactionDataSourceGatewayImpl : TransactionDataSourceGateway,
    ReactiveCrudRepository<UserTransaction, Int> {

    override fun findByUserId(userId: Int): Flux<UserTransaction>
}