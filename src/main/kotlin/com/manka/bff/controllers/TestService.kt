package com.manka.bff.controllers

import com.manka.bff.infra.MessageStructure
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.header.internals.RecordHeaders
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.kafka.requestreply.RequestReplyFuture
import org.springframework.kafka.support.KafkaHeaders
import java.util.concurrent.TimeUnit
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service


@Service
class TestService(
    val template: ReplyingKafkaTemplate<String, Any, Any>
) {

    //KFT
//    Type parameters:
//    <K> – the key type. <V> – the outbound data type. <R> – the reply data type.

    @Throws(Exception::class)
    fun kafkaRequestReply(request: MessageStructure): Any {
        val record = ProducerRecord<String, Any>(request.sendTo, request.content)
        record.headers().add(RecordHeader(KafkaHeaders.REPLY_TOPIC, request.replyTo.toByteArray()))
        record.headers().add("timeStamp", request.timeStamp.toString().toByteArray())
        val replyFuture: RequestReplyFuture<String, Any, Any> =
            template.sendAndReceive(record)
        val sendResult: SendResult<String, Any> = replyFuture.sendFuture[10, TimeUnit.SECONDS]
        val consumerRecord = replyFuture[10, TimeUnit.SECONDS]
        return consumerRecord.value()
    }
}

