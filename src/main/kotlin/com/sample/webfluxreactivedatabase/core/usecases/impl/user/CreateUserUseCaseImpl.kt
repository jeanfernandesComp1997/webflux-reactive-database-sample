package com.sample.webfluxreactivedatabase.core.usecases.impl.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserDtoMapper
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserMapper
import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.user.CreateUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CreateUserUseCaseImpl(
    private val userDataSourceGateway: UserDataSourceGateway,
    private val toUserDtoMapper: ToUserDtoMapper,
    private val toUserMapper: ToUserMapper
) : CreateUserUseCase {

    override fun execute(user: Mono<UserDto>): Mono<UserDto> {
        return user
            .map(toUserMapper::map)
            .flatMap(userDataSourceGateway::save)
            .map(toUserDtoMapper::map)
    }
}