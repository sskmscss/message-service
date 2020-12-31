/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service

import kotlin.jvm.JvmStatic
import java.lang.Exception

/**
 * Service for subscriber.
 * 1. Build and install jars:
 * mvn clean install
 * 2. Run the server:
 * dapr run --components-path ./components --app-id subscriber --app-port 3000 --dapr-http-port 3005 -- \
 * java -jar target/message-service-exec.jar scm.service.Subscriber -p 3000
 */
object Subscriber {
    /**
     * This is the entry point for this example app, which subscribes to a topic.
     * @param args The port this app will listen on.
     * @throws Exception An Exception on startup.
     */
    @Throws(Exception::class)
    @JvmStatic
    fun mainFunction(args: Array<String>) {
        val replaceWith = Regex(".service.*")
        var getClass = Config.getCMD(args)?.getOptionValue("class")

        if (getClass.isNullOrEmpty()) {
            getClass = "dapr"
        }

        val className = replaceWith.replace(args[0],".service." + getClass + ".subscriber.Subscriber")
        println("Class :: " + className)
        val mainClass = Class.forName(className)
        val methodArgs = arrayOfNulls<Any>(1)
        methodArgs[0] = arrayOf(String.format("--server.port=%d", Config.getCMD(args)?.getOptionValue("port")?.toInt()))
        mainClass.getDeclaredMethod("start", Array<String>::class.java).invoke(
                mainClass.getDeclaredConstructor().newInstance(),
                *methodArgs
        )
    }
}
