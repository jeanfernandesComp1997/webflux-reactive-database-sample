package com.sample.webfluxreactivedatabase.application.rest.controller

import com.sample.webfluxreactivedatabase.application.dto.models.UserDto
import com.sample.webfluxreactivedatabase.application.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userRequest: Mono<UserDto>): Mono<UserDto> {
        return userService
            .createUser(userRequest)
    }

    @GetMapping
    fun all(): Flux<UserDto> {
        return userService
            .all()
    }

    @GetMapping("{id}")
    fun retrieveById(@PathVariable id: Int): Mono<ResponseEntity<UserDto>> {
        return userService
            .retrieveById(id)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody userRequest: Mono<UserDto>): Mono<ResponseEntity<UserDto>> {
        return userService
            .updateUser(userRequest)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Int): Mono<Void> {
        return userService
            .deleteUser(id)
    }
}