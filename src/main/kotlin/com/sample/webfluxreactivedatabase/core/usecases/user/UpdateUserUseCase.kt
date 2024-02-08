package com.sample.webfluxreactivedatabase.core.usecases.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import reactor.core.publisher.Mono

interface UpdateUserUseCase {

    fun execute(user: Mono<UserDto>): Mono<UserDto>
}