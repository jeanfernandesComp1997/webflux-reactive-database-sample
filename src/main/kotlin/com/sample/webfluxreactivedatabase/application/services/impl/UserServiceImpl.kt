package com.sample.webfluxreactivedatabase.application.services.impl

import com.sample.webfluxreactivedatabase.application.services.UserService
import com.sample.webfluxreactivedatabase.application.dto.models.UserDto
import com.sample.webfluxreactivedatabase.application.mapper.ToUserDtoMapper
import com.sample.webfluxreactivedatabase.application.mapper.ToUserMapper
import com.sample.webfluxreactivedatabase.infrastructure.data.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val toUserDtoMapper: ToUserDtoMapper,
    private val toUserMapper: ToUserMapper
) : UserService {

    override fun all(): Flux<UserDto> {
        return userRepository.findAll()
            .map(toUserDtoMapper::map)
    }

    override fun retrieveById(id: Int): Mono<UserDto> {
        return userRepository.findById(id)
            .map(toUserDtoMapper::map)
    }

    override fun createUser(user: Mono<UserDto>): Mono<UserDto> {
        return user
            .map(toUserMapper::map)
            .flatMap(userRepository::save)
            .map(toUserDtoMapper::map)
    }

    override fun updateUser(user: Mono<UserDto>): Mono<UserDto> {
        return user
            .flatMap { userDto ->
                userRepository.findById(userDto.id)
                    .map {
                        toUserMapper.map(userDto)
                    }
                    .flatMap(userRepository::save)
                    .map(toUserDtoMapper::map)
            }
    }

    override fun deleteUser(id: Int): Mono<Void> {
        return userRepository.deleteById(id)
    }
}