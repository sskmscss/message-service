/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.kafka.publisher

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.core.KafkaTemplate
import scm.utility.Config

@SpringBootApplication
class Publisher {
    fun start(args: Array<String>) {
        try {
            val context = SpringApplication.run(Publisher::class.java, *args)
            val kafkaTemplate: KafkaTemplate<String, Any> = context.getBean(KafkaTemplate::class.java) as KafkaTemplate<String, Any>
            val testObject = object {
                var data: String = "Publish StoreorderRef"
            }

            kafkaTemplate.send(Config.properties!!["topic"].toString(), testObject)
            println("KAFKA MESSAGE PUBLISHED")
        } catch (e: Exception) {
            println("EXCEPTIONS :: " + e)
        }
    }
}
