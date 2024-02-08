package com.sample.webfluxreactivedatabase.core.usecases.impl.transaction

import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import com.sample.webfluxreactivedatabase.core.gateway.transaction.TransactionDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.transaction.RetrieveTransactionByUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class RetrieveTransactionByUserUseCaseImpl(
    private val transactionRepository: TransactionDataSourceGateway,
) : RetrieveTransactionByUserUseCase {

    override fun execute(userId: Int): Flux<UserTransaction> {
        return transactionRepository.findByUserId(userId)
    }
}