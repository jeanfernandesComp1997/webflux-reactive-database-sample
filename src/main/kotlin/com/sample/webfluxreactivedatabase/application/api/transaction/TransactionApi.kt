package com.sample.webfluxreactivedatabase.application.api.transaction

import com.sample.webfluxreactivedatabase.core.domain.dto.models.TransactionDto
import com.sample.webfluxreactivedatabase.core.domain.dto.output.TransactionResponse
import com.sample.webfluxreactivedatabase.core.domain.entity.UserTransaction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("users/transactions")
interface TransactionApi {

    @PostMapping
    fun createTransaction(@RequestBody request: Mono<TransactionDto>): Mono<TransactionResponse>

    @GetMapping("{id}")
    fun retrieveTransactionById(@PathVariable id: Int): Mono<TransactionDto>

    @GetMapping
    fun retrieveByUserId(@RequestParam userId: Int): Flux<UserTransaction>
}