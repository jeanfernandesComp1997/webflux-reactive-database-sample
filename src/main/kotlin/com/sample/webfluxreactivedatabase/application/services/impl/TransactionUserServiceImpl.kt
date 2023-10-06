package com.sample.webfluxreactivedatabase.application.services.impl

import com.sample.webfluxreactivedatabase.application.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.application.dto.output.TransactionResponse
import com.sample.webfluxreactivedatabase.application.mapper.ToUserTransactionDtoMapper
import com.sample.webfluxreactivedatabase.application.mapper.ToUserTransactionMapper
import com.sample.webfluxreactivedatabase.application.mapper.ToUserTransactionResponseCustomMapper
import com.sample.webfluxreactivedatabase.application.services.TransactionService
import com.sample.webfluxreactivedatabase.domain.entity.UserTransaction
import com.sample.webfluxreactivedatabase.domain.enums.TransactionStatus
import com.sample.webfluxreactivedatabase.infrastructure.data.repository.UserRepository
import com.sample.webfluxreactivedatabase.infrastructure.data.repository.UserTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TransactionUserServiceImpl(
    private val userRepository: UserRepository,
    private val transactionRepository: UserTransactionRepository,
    private val toUserTransactionDtoMapper: ToUserTransactionDtoMapper,
    private val toUserTransactionMapper: ToUserTransactionMapper,
    private val toUserTransactionResponseCustomMapper: ToUserTransactionResponseCustomMapper
) : TransactionService {

    override fun retrieveById(id: Int): Mono<TransactionDto> {
        return transactionRepository
            .findById(id)
            .map {
                toUserTransactionDtoMapper.map(it)
            }
    }

    @Transactional
    override fun createTransaction(transactionRequest: TransactionDto): Mono<TransactionResponse> {
        return userRepository
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

    override fun retrieveByUserId(userId: Int): Flux<UserTransaction> {
        return transactionRepository.findByUserId(userId)
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