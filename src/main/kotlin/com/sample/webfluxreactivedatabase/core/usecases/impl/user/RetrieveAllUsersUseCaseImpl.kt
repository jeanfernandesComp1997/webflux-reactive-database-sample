package com.sample.webfluxreactivedatabase.core.usecases.impl.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserDtoMapper
import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.user.RetrieveAllUsersUseCase
import com.sample.webfluxreactivedatabase.gateway.user.UserDataSourceGatewayImpl
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class RetrieveAllUsersUseCaseImpl(
    private val userDataSourceGateway: UserDataSourceGateway,
    private val toUserDtoMapper: ToUserDtoMapper
) : RetrieveAllUsersUseCase {

    override fun execute(): Flux<UserDto> {
        return userDataSourceGateway.findAll()
            .map(toUserDtoMapper::map)
    }
}