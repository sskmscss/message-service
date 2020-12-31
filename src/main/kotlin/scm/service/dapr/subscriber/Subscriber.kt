/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.dapr.subscriber

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import scm.service.um.Config

/**
 * Dapr's HTTP callback implementation via SpringBoot.
 * Scanning package io.dapr.springboot is required.
 */
@SpringBootApplication(scanBasePackages = ["io.dapr.springboot", "scm.service.dapr.subscriber","scm.controller.subscriber"])
class Subscriber {
    fun start(args: Array<String>) {
        runApplication<Subscriber>(*args)
    }
}