package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import reactor.core.publisher.Mono

internal interface FindSubmittedLeaveRequest {
    fun findSubmitted(requestId: LeaveRequestId): Mono<SubmittedLeaveRequest>
}