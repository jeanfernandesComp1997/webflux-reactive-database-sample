package com.sample.webfluxreactivedatabase.application.api.user

import com.sample.webfluxreactivedatabase.core.domain.dto.models.UserDto
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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("users")
interface UserApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userRequest: Mono<UserDto>): Mono<UserDto>

    @GetMapping
    fun all(): Flux<UserDto>

    @GetMapping("{id}")
    fun retrieveById(@PathVariable id: Int): Mono<ResponseEntity<UserDto>>

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody userRequest: Mono<UserDto>): Mono<ResponseEntity<UserDto>>

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Int): Mono<Void>
}