/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.um.publisher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import scm.service.um.Config

/**
 * Dapr's HTTP callback implementation via SpringBoot.
 */
@SpringBootApplication
class Publisher {
    fun start(args: Array<String>) {
        runApplication<scm.service.dapr.publisher.Publisher>(*args)
        Config.publish("Change Stream Called to UM")
    }
}
