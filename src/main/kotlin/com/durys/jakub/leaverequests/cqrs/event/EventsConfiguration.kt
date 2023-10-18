package com.durys.jakub.leaverequests.cqrs.event

import com.durys.jakub.leaverequests.cqrs.event.rabbitmq.RabbitmqEventPublisher
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class EventsConfiguration {

    @Bean
    fun eventPublisher(rabbitmqTemplate: RabbitTemplate, @Value("\${queue.leave-entitlements}") queue: String): Events =
            RabbitmqEventPublisher(rabbitmqTemplate, queue)

}