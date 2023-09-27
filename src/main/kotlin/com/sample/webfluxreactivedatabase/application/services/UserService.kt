package com.sample.webfluxreactivedatabase.application.services

import com.sample.webfluxreactivedatabase.application.dto.models.UserDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    fun all(): Flux<UserDto>
    fun retrieveById(id: Int): Mono<UserDto>
    fun createUser(user: Mono<UserDto>): Mono<UserDto>
    fun updateUser(user: Mono<UserDto>): Mono<UserDto>
    fun deleteUser(id: Int): Mono<Void>
}