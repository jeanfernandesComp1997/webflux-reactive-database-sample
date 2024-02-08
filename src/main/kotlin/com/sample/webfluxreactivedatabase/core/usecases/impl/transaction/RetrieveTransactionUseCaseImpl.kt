package com.sample.webfluxreactivedatabase.core.usecases.impl.transaction

import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserTransactionDtoMapper
import com.sample.webfluxreactivedatabase.core.gateway.transaction.TransactionDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.transaction.RetrieveTransactionUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RetrieveTransactionUseCaseImpl(
    private val transactionRepository: TransactionDataSourceGateway,
    private val toUserTransactionDtoMapper: ToUserTransactionDtoMapper,
) : RetrieveTransactionUseCase {

    override fun execute(id: Int): Mono<TransactionDto> {
        return transactionRepository
            .findById(id)
            .map {
                toUserTransactionDtoMapper.map(it)
            }
    }
}