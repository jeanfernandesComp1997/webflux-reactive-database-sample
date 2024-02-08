package com.sample.webfluxreactivedatabase.application.controller

import com.sample.webfluxreactivedatabase.application.api.user.UserApi
import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import com.sample.webfluxreactivedatabase.core.usecases.user.CreateUserUseCase
import com.sample.webfluxreactivedatabase.core.usecases.user.DeleteUserUseCase
import com.sample.webfluxreactivedatabase.core.usecases.user.RetrieveAllUsersUseCase
import com.sample.webfluxreactivedatabase.core.usecases.user.RetrieveUserUseCase
import com.sample.webfluxreactivedatabase.core.usecases.user.UpdateUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val retrieveUserUseCase: RetrieveUserUseCase,
    private val retrieveAllUsersUseCase: RetrieveAllUsersUseCase
) : UserApi {

    override fun create(userRequest: Mono<UserDto>): Mono<UserDto> {
        return createUserUseCase
            .execute(userRequest)
    }

    override fun all(): Flux<UserDto> {
        return retrieveAllUsersUseCase
            .execute()
    }

    override fun retrieveById(id: Int): Mono<ResponseEntity<UserDto>> {
        return retrieveUserUseCase
            .execute(id)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    override fun update(userRequest: Mono<UserDto>): Mono<ResponseEntity<UserDto>> {
        return updateUserUseCase
            .execute(userRequest)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    override fun deleteById(id: Int): Mono<Void> {
        return deleteUserUseCase
            .execute(id)
    }
}