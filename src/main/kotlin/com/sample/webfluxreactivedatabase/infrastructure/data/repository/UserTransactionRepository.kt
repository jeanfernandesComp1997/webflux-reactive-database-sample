package com.sample.webfluxreactivedatabase.infrastructure.data.repository

import com.sample.webfluxreactivedatabase.domain.entity.UserTransaction
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserTransactionRepository : ReactiveCrudRepository<UserTransaction, Int>