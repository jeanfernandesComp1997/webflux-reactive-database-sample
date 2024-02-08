package com.sample.webfluxreactivedatabase.gateway.user

import com.sample.webfluxreactivedatabase.core.domain.entity.User
import com.sample.webfluxreactivedatabase.core.gateway.user.UserDataSourceGateway
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserDataSourceGatewayImpl : UserDataSourceGateway, ReactiveCrudRepository<User, Int> {

    @Modifying
    @Query("update users set balance = balance - :amount where id = :userId and balance >= :amount")
    override fun updateUserBalance(userId: Int, amount: Double): Mono<Boolean>
}