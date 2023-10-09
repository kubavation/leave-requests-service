package com.durys.jakub.leaverequests.entitlements.infrastructure

import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlementsRepository
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
internal class LeaveEntitlementsConfiguration {

    @Bean
    @LoadBalanced
    fun leaveEntitlementsClient(): WebClient.Builder? {
        return WebClient.builder()
                .baseUrl("http://LEAVE-ENTITLEMENTS-SERVICE")
    }

    @Bean
    fun leaveEntitlementsRepository(leaveEntitlementsClient: WebClient.Builder): LeaveEntitlementsRepository {
        return RestLeaveEntitlementsRepository(leaveEntitlementsClient.baseUrl("/api/leave-entitlements").build())
    }

}