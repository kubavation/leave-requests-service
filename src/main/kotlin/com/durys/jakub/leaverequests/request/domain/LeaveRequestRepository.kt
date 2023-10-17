package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.flow.FindSubmittedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import reactor.core.publisher.Mono

internal interface LeaveRequestRepository: FindSubmittedLeaveRequest {
    fun load(id: LeaveRequestId): Mono<LeaveRequest>
    fun save(leaveRequest: LeaveRequest)
}