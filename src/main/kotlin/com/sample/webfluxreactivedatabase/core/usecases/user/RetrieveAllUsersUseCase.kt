package com.sample.webfluxreactivedatabase.core.usecases.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import reactor.core.publisher.Flux

interface RetrieveAllUsersUseCase {

    fun execute(): Flux<UserDto>
}