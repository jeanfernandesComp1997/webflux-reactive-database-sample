package com.sample.webfluxreactivedatabase.core.usecases.impl.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserDtoMapper
import com.sample.webfluxreactivedatabase.shared.mapper.ToUserMapper
import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import com.sample.webfluxreactivedatabase.core.usecases.user.UpdateUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UpdateUserUseCaseImpl(
    private val userDataSourceGateway: UserDataSourceGateway,
    private val toUserDtoMapper: ToUserDtoMapper,
    private val toUserMapper: ToUserMapper
) : UpdateUserUseCase {

    override fun execute(user: Mono<UserDto>): Mono<UserDto> {
        return user
            .flatMap { userDto ->
                userDataSourceGateway.findById(userDto.id)
                    .map {
                        toUserMapper.map(userDto)
                    }
                    .flatMap(userDataSourceGateway::save)
                    .map(toUserDtoMapper::map)
            }
    }
}