package com.sample.webfluxreactivedatabase.core.usecases.impl.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserDtoMapper
import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.user.RetrieveUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class RetrieveUserUseCaseImpl(
    private val userDataSourceGateway: UserDataSourceGateway,
    private val toUserDtoMapper: ToUserDtoMapper,
) : RetrieveUserUseCase {

    override fun execute(id: Int): Mono<UserDto> {
        return userDataSourceGateway.findById(id)
            .map(toUserDtoMapper::map)
    }
}