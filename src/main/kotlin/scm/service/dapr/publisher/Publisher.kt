/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.dapr.publisher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Dapr's HTTP callback implementation via SpringBoot.
 */
@SpringBootApplication(scanBasePackages = ["scm.service.dapr.publisher", "scm.controller.publisher"])
class Publisher {
    fun start(args: Array<String>) {
        runApplication<Publisher>(*args)
    }
}