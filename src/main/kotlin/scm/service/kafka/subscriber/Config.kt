package scm.service.kafka.subscriber

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import java.util.*

@EnableKafka
@Configuration
class Config {
    @Value("\${kafka.host:localhost}")
    private val host: String? = null

    @Value("\${kafka.port:9092}")
    private val port: Int = 0

    @Bean
    fun consumerConfigs(): Map<String, Any> {
        println("Configs")
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        configProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configProps[ConsumerConfig.GROUP_ID_CONFIG] = "kafka-subscribe"
        return configProps
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        println("Configs")
        return DefaultKafkaConsumerFactory(consumerConfigs())
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()

        return factory
    }

    companion object {
        const val TOPIC = "StoreOrderReference"
    }
}
