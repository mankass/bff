package com.manka.bff.infra

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MessageStructureDeserializer : Deserializer<MessageStructure> {
    private val objectMapper = jacksonObjectMapper()

    private val logger = LoggerFactory.getLogger(MessageStructureDeserializer::class.java)

    override fun deserialize(p0: String?, data: ByteArray?): MessageStructure {
        try {
            return objectMapper.readValue(data, MessageStructure::class.java)
        } catch (e: Exception) {
            logger.error("Error when deserializing byte[] to MessageDto")
            throw e
        }
    }
}