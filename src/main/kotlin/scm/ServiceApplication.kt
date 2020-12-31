package scm

import java.util.*

/**
 * Service Application to run using fatjar. This class will call the main source provided by user dynamically.
 */

class ServiceApplication

fun main(args: Array<String>) {
	println(Arrays.toString(args))
	// Local run
		val local: Array<String> = arrayOf("scm.service.Subscriber", "-p", "3000", "-c", "kafka")
    //	val local: Array<String> = arrayOf("scm.service.Subscriber", "-p", "3000")

	val arguments: Array<String>

	if (args.size >= 1) {
		require(args.size >= 1) { "Requires at least one argument - name of the main class" }
		arguments = args.copyOfRange(0, args.size)
	} else {
		arguments = local.copyOfRange(0, local.size)
	}

	val mainClass = Class.forName(arguments[0])
	val mainMethod = mainClass.getDeclaredMethod("mainFunction", Array<String>::class.java)
	val methodArgs = arrayOfNulls<Any>(1)
	methodArgs[0] = arguments

	mainMethod.invoke(mainClass, *methodArgs)
}
