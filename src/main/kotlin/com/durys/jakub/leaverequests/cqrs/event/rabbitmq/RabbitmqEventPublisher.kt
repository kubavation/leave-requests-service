package com.durys.jakub.leaverequests.cqrs.event.rabbitmq

import com.durys.jakub.leaverequests.cqrs.event.Event
import com.durys.jakub.leaverequests.cqrs.event.Events
import org.springframework.amqp.rabbit.core.RabbitTemplate

internal class RabbitmqEventPublisher(private val rabbitTemplate: RabbitTemplate, private val queue: String): Events {

    override fun <T: Event> emit(event: T) {
        rabbitTemplate.convertAndSend(queue, event)
    }
}