package com.sample.webfluxreactivedatabase.shared.mapper

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
import com.sample.webfluxreactivedatabase.core.domain.entity.User
import org.springframework.stereotype.Component

@Component
class ToUserDtoMapper : Mapper<User, UserDto> {

    override fun map(t: User): UserDto {
        return UserDto(t.id, t.name, t.balance)
    }
}

@Component
class ToUserMapper : Mapper<UserDto, User> {

    override fun map(t: UserDto): User {
        return User(t.id, t.name, t.balance)
    }
}