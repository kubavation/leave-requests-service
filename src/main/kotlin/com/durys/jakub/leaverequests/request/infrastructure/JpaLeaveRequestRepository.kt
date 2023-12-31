package com.durys.jakub.leaverequests.request.infrastructure

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.flow.SentForAcceptationLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.SubmittedLeaveRequest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import reactor.core.publisher.Mono

internal class JpaLeaveRequestRepository(val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): LeaveRequestRepository {

    override fun load(id: LeaveRequestId): Mono<LeaveRequest> {
        TODO("Not yet implemented")
    }

    override fun save(leaveRequest: LeaveRequest) {
        TODO("Not yet implemented")
    }

    override fun findSubmitted(requestId: LeaveRequestId): Mono<SubmittedLeaveRequest> {
        TODO("Not yet implemented")
    }

    override fun findSentForAcceptation(requestId: LeaveRequestId): Mono<SentForAcceptationLeaveRequest> {
        TODO("Not yet implemented")
    }
}