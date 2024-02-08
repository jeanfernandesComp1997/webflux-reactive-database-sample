package com.sample.webfluxreactivedatabase.core.usecases.impl.user

import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.user.DeleteUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class DeleteUserUseCaseImpl(
    private val userDataSourceGateway: UserDataSourceGateway,
) : DeleteUserUseCase {

    override fun execute(id: Int): Mono<Void> {
        return userDataSourceGateway.deleteById(id)
    }
}