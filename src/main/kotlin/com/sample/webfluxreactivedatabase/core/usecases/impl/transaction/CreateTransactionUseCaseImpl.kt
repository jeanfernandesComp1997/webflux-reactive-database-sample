package com.sample.webfluxreactivedatabase.core.usecases.impl.transaction

import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.core.domain.dto.output.TransactionResponse
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserTransactionMapper
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserTransactionResponseCustomMapper
import com.sample.webfluxreactivedatabase.core.domain.enums.TransactionStatus
import com.sample.webfluxreactivedatabase.core.gateway.transaction.TransactionDataSourceGateway
import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.transaction.CreateTransactionUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class CreateTransactionUseCaseImpl(
    private val userDataSourceGateway: UserDataSourceGateway,
    private val transactionRepository: TransactionDataSourceGateway,
    private val toUserTransactionMapper: ToUserTransactionMapper,
    private val toUserTransactionResponseCustomMapper: ToUserTransactionResponseCustomMapper
) : CreateTransactionUseCase {

    @Transactional
    override fun execute(transactionRequest: TransactionDto): Mono<TransactionResponse> {
        return userDataSourceGateway
            .updateUserBalance(transactionRequest.userId, transactionRequest.amount)
            .filter { result ->
                result == true
            }
            .map {
                toUserTransactionMapper.map(transactionRequest)
            }
            .flatMap { userTransaction ->
                transactionRepository.save(userTransaction)
            }
            .map { userTransaction ->
                toUserTransactionResponseCustomMapper.map(userTransaction, TransactionStatus.APPROVED)
            }
            .defaultIfEmpty(buildDeclinedResponse(transactionRequest))
    }

    private fun buildDeclinedResponse(transactionRequest: TransactionDto): TransactionResponse {
        return TransactionResponse(
            id = 0,
            userId = transactionRequest.userId,
            amount = transactionRequest.amount,
            status = TransactionStatus.DECLINED
        )
    }
}