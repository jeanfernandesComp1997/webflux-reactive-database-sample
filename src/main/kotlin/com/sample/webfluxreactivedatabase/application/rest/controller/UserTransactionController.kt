package com.sample.webfluxreactivedatabase.application.rest.controller

import com.sample.webfluxreactivedatabase.application.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.application.dto.output.TransactionResponse
import com.sample.webfluxreactivedatabase.application.services.TransactionService
import com.sample.webfluxreactivedatabase.domain.entity.UserTransaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("users/transactions")
class UserTransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping
    fun createTransaction(@RequestBody request: Mono<TransactionDto>): Mono<TransactionResponse> {
        return request
            .flatMap { transactionRequest ->
                transactionService.createTransaction(transactionRequest)
            }
    }

    @GetMapping("{id}")
    fun retrieveTransactionById(@PathVariable id: Int): Mono<TransactionDto> {
        return transactionService.retrieveById(id)
    }

    @GetMapping
    fun retrieveByUserId(@RequestParam userId: Int): Flux<UserTransaction> {
        return transactionService.retrieveByUserId(userId)
    }
}