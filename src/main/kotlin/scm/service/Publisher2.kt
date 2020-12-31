/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service

import io.dapr.v1.AppCallbackGrpc
import io.dapr.v1.CommonProtos
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import java.io.IOException

/**
 * NOT IN USE. NEED FOR DAPR BINDING. NEEDS TO WORK
 */
object Publisher2 {
    /**
     * This is the main method of this app.
     * @param args The port to listen on.
     * @throws Exception An Exception.
     */
    @Throws(Exception::class)
    @JvmStatic
    fun mainFunction(args: Array<String>) {
        val options = Options()
        options.addRequiredOption("p", "port", true, "Port to listen to.")
        val parser: CommandLineParser = DefaultParser()
        val cmd: CommandLine = parser.parse(options, args)

        // If port string is not valid, it will throw an exception.
        val port = cmd.getOptionValue("port").toInt()
        val service = GrpcHelloWorldDaprService()
        service.start(port)
        service.awaitTermination()
    }

    /**
     * Server mode: class that encapsulates all server-side logic for Grpc.
     */
    private class GrpcHelloWorldDaprService : AppCallbackGrpc.AppCallbackImplBase() {
        /**
         * Server mode: Grpc server.
         */
        private var server: Server? = null

        /**
         * Server mode: starts listening on given port.
         *
         * @param port Port to listen on.
         * @throws IOException Errors while trying to start service.
         */
        @Throws(IOException::class)
        fun start(port: Int) {
            server = ServerBuilder
                    .forPort(port)
                    .addService(this)
                    .build()
                    .start()
            System.out.printf("Server: started listening on port %d\n", port)

            // Now we handle ctrl+c (or any other JVM shutdown)
            Runtime.getRuntime().addShutdownHook(object : Thread() {
                override fun run() {
                    println("Server: shutting down gracefully ...")
                    server!!.shutdown()
                    println("Server: Bye.")
                }
            })
        }

        /**
         * Server mode: waits for shutdown trigger.
         *
         * @throws InterruptedException Propagated interrupted exception.
         */
        @Throws(InterruptedException::class)
        fun awaitTermination() {
            if (server != null) {
                server!!.awaitTermination()
            }
        }

        /**
         * Server mode: this is the Dapr method to receive Invoke operations via Grpc.
         *
         * @param request          Dapr envelope request,
         * @param responseObserver Dapr envelope response.
         */
         override fun onInvoke(request: CommonProtos.InvokeRequest,
                               responseObserver: StreamObserver<CommonProtos.InvokeResponse>) {
            try {
                println("REQUEST METHOD :: " + request.getMethod())
                if ("say" == request.getMethod()) {
                    println("RESPONSE :: " + request.getData().getValue().toStringUtf8())
                }
            } finally {

            }
        }
    }
}