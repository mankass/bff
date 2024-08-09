package com.manka.bff.kafka

import com.manka.bff.infra.MessageStructure
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate


@Configuration
class KafkaConfiguration {

//    @Bean
//    fun producerFactory(): ProducerFactory<String, String> {
//        val configProps = mutableMapOf<String, Any>()
//        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:29092"
//        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] =
//            org.apache.kafka.common.serialization.StringSerializer::class.java
//        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] =
//            org.apache.kafka.common.serialization.StringSerializer::class.java
//
//        return DefaultKafkaProducerFactory(configProps)
//    }

    @Bean //register and configure replying kafka template
    fun replyingTemplate(
        pf: ProducerFactory<String, MessageStructure>?,
        repliesContainer:ConcurrentMessageListenerContainer<String, Any>
    ): ReplyingKafkaTemplate<String, MessageStructure, Any> {
        return ReplyingKafkaTemplate(pf, repliesContainer)
    }

    //register ConcurrentMessageListenerContainer bean
    @Bean
    fun repliesContainer(
        containerFactory: ConcurrentKafkaListenerContainerFactory<String, Any>,
    ): ConcurrentMessageListenerContainer<String, Any> {
        val repliesContainer: ConcurrentMessageListenerContainer<String, Any> =
            containerFactory.createContainer("replay")
        repliesContainer.containerProperties.setGroupId("groupId")
        repliesContainer.isAutoStartup = true
        return repliesContainer
    }
}