package com.sample.webfluxreactivedatabase.core.gateway.user

import com.sample.webfluxreactivedatabase.core.domain.entity.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserDataSourceGateway {

    fun updateUserBalance(userId: Int, amount: Double): Mono<Boolean>
    fun save(user: User): Mono<User>
    fun deleteById(id: Int): Mono<Void>
    fun findAll(): Flux<User>
    fun findById(id: Int): Mono<User>
}