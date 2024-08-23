package com.breader.springdatamongodbissueskotlin

import org.springframework.data.mongodb.repository.Update
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CoroutineCrudRepository<Person, String> {
    @Update("{ \$inc: { age: 1 } }")
    suspend fun findAndIncrementAgeByName(name: String)
}