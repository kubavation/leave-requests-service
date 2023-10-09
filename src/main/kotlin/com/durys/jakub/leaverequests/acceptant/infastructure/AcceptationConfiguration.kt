package com.durys.jakub.leaverequests.acceptant.infastructure

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
internal class AcceptationConfiguration {

    @Bean
    @LoadBalanced
    fun acceptationClient(): WebClient.Builder? {
        return WebClient.builder()
                .baseUrl("http://ACCEPTATION-SERVICE")
    }

    @Bean
    fun acceptantRepository(acceptationClient: WebClient.Builder): AcceptantRepository {
        return RestAcceptantRepository(acceptationClient.baseUrl("/api/acceptation").build())
    }

}