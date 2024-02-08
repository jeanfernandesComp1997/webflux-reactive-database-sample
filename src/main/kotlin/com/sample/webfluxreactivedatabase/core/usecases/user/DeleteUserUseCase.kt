package com.sample.webfluxreactivedatabase.core.usecases.user

import reactor.core.publisher.Mono

interface DeleteUserUseCase {

    fun execute(id: Int): Mono<Void>
}