/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service

import kotlin.jvm.JvmStatic
import java.lang.Exception

/**
 * Message publisher.
 * 1. Build and install jars:
 * mvn clean install
 * 2. Run the program:
 * dapr run --components-path ./components --app-id publisher --app-port 8085 --dapr-http-port 3006 -- \
 * java -jar  target/dapr-service-exec.jar scm.service.Publisher -p 8085
 */
object Publisher {
    private const val NUM_MESSAGES = 1

    /**
     * This is the entry point of the publisher app example.
     * @param args Args, unused.
     * @throws Exception A startup Exception.
     */
    @Throws(Exception::class)
    @JvmStatic
    fun mainFunction(args: Array<String>) {
        val replaceWith = Regex(".service.*")
        var getClass = Config.getCMD(args)?.getOptionValue("class")

        if (getClass.isNullOrEmpty()) {
            getClass = "dapr"
        }

        val className = replaceWith.replace(args[0],".service." + getClass + ".publisher.Publisher")
        val mainClass = Class.forName(className)
        val methodArgs = arrayOfNulls<Any>(1)
        methodArgs[0] = arrayOf(String.format("--server.port=%d", Config.getCMD(args)?.getOptionValue("port")?.toInt()))
        mainClass.getDeclaredMethod("start", Array<String>::class.java).invoke(
                mainClass.getDeclaredConstructor().newInstance(),
                *methodArgs
        )
    }
}