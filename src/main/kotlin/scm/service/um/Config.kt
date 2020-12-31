package scm.service.um

import com.pcbsys.nirvana.client.*
import scm.utility.Config

object Config {
    var session: nSession? = null

    init {
        val nsa = nSessionAttributes(arrayOf(Config.properties!!["UM_REMOTE_ADDRESS"].toString()))
        session = nSessionFactory.create(nsa)
        session?.init()
    }

    fun channel(): nChannel? {
        val channelAttribute = nChannelAttributes()
        channelAttribute.setName(Config.properties!!["topic"].toString())
        return session?.findChannel(channelAttribute)
    }

    fun publish(msg: String) {
        val props: nEventProperties = nEventProperties()
        props.put("data", msg)
        channel()?.publish(nConsumeEvent("atag", props, "data".toByteArray()))
    }
}
