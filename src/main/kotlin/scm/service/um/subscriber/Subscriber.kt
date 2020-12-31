/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service.um.subscriber

import com.pcbsys.nirvana.client.nBaseClientException
import com.pcbsys.nirvana.client.nConsumeEvent
import com.pcbsys.nirvana.client.nEventListener
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import scm.service.um.Config

/**
 * Dapr's HTTP callback implementation via SpringBoot.
 */
@SpringBootApplication
class Subscriber: nEventListener {
    override fun go(event: nConsumeEvent) {
        try {
            println("Consumed event " + event.fullChannelName)
            println("data retrieved::" + String(event.eventData))
        } catch (e: nBaseClientException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        println(event.properties)
    }

    fun start(args: Array<String>) {
        runApplication<Subscriber>(*args)
        Config.channel()?.addSubscriber(this, 0)
    }
}
