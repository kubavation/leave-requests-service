package com.durys.jakub.leaverequests.request.infrastructure

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

internal class JpaLeaveRequestRepository(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): LeaveRequestRepository {

    override fun load(id: LeaveRequestId): LeaveRequest? {
        TODO("Not yet implemented")
    }

    override fun save(leaveRequest: LeaveRequest) {
        TODO("Not yet implemented")
    }
}