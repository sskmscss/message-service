/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.activemq.publisher

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.jms.core.JmsTemplate

/**
 * Dapr's HTTP callback implementation via SpringBoot.
 */
@SpringBootApplication(scanBasePackages = ["scm.service.jms"])
class Publisher {
    fun start(args: Array<String>) {
        val context = SpringApplication.run(Publisher::class.java, *args)
        val jmsTemplate: JmsTemplate = context.getBean(JmsTemplate::class.java)
        val springTopic = jmsTemplate.connectionFactory?.createConnection()?.createSession()?.createTopic("StoreOrderReference")
        val message = String.format("This is message")

        if (springTopic != null) {
            try {
                jmsTemplate.convertAndSend(springTopic, message)
            } catch(e: Exception) {
                println(e)
            }
        }
    }
}