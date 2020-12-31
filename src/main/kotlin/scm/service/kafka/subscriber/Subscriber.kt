/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.kafka.subscriber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener
import scm.service.kafka.subscriber.Config.Companion.TOPIC

/**
 * Kafka Subscriber implementation via SpringBoot.
 */
@SpringBootApplication
class Subscriber {
    @KafkaListener(topics = [TOPIC])
    fun receive(payload: String) {
        println("MESSAGE RECEIVED :: " + payload)
    }

    fun start(args: Array<String>) {
        runApplication<Subscriber>(*args)
    }
}
