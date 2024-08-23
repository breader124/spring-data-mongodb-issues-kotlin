package com.breader.springdatamongodbissueskotlin

import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class SpringDataMongodbIssuesKotlinApplication(val personRepository: PersonRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        runBlocking {
            val personId = UUID.randomUUID().toString()

            Person(id = personId, name = "John Doe", age = 30)
                .let { personRepository.save(it) }
                .let { personRepository.findAndIncrementAgeByName(it.name) }
                .also { println("Modified $it documents") }

            personRepository.findById(personId)
                ?.let { println(it) }
                ?: println("Didn't find a person")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringDataMongodbIssuesKotlinApplication>(*args)
}
