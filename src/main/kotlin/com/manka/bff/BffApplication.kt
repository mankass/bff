package com.manka.bff

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class BffApplication

fun main(args: Array<String>) {
    runApplication<BffApplication>(*args)
}
