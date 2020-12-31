package scm.service

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options

object Config {
    var cmd: CommandLine? = null
    fun getCMD(args: Array<String>): CommandLine? {
        if (cmd == null) {
            val options = Options()

            options.addRequiredOption("p", "port", true, "The port this app will listen on")
            options.addOption("c", "class", true, "The service class needs to be invoked")
            val parser: CommandLineParser = DefaultParser()
            cmd = parser.parse(options, args)
        }
        return cmd
    }
}