package com.manka.bff.infra

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MessageStructureSerializer: Serializer<MessageStructure> {
    private val objectMapper = jacksonObjectMapper()

    private val logger = LoggerFactory.getLogger(MessageStructureSerializer::class.java)
    override fun serialize(p0: String?, data: MessageStructure?): ByteArray {
        try {
            return objectMapper.writeValueAsBytes(data)
        } catch (e: JsonProcessingException) {
            logger.error("Unable to serialize object {}", data, e)
            throw e
        }
    }
}