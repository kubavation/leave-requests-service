package com.durys.jakub.leaverequests.request.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
internal class LeaveRequestConfiguration {

    @Bean
    fun leaveRequestRepository(namedParameterJdbcTemplate: NamedParameterJdbcTemplate) = JpaLeaveRequestRepository(namedParameterJdbcTemplate)

}