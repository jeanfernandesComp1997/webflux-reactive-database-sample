package com.sample.webfluxreactivedatabase.shared.mapper

import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.core.domain.dto.output.TransactionResponse
import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import com.sample.webfluxreactivedatabase.core.domain.enums.TransactionStatus
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ToUserTransactionMapper : Mapper<TransactionDto, UserTransaction> {

    override fun map(t: TransactionDto): UserTransaction {
        return UserTransaction(
            id = t.id,
            userId = t.userId,
            amount = t.amount,
            transactionDate = LocalDateTime.now()
        )
    }
}

@Component
class ToUserTransactionResponseCustomMapper : CustomMapper<UserTransaction, TransactionResponse> {

    override fun map(t: UserTransaction, status: TransactionStatus): TransactionResponse {
        return TransactionResponse(
            id = t.id,
            userId = t.userId,
            amount = t.amount,
            status = status
        )
    }
}

@Component
class ToUserTransactionDtoMapper : Mapper<UserTransaction, TransactionDto> {

    override fun map(t: UserTransaction): TransactionDto {
        return TransactionDto(
            id = t.id,
            userId = t.userId,
            amount = t.amount
        )
    }
}