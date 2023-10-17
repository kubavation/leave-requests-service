package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import reactor.core.publisher.Mono

internal interface FindSentForAcceptationLeaveRequest {
    fun find(requestId: LeaveRequestId): Mono<SubmittedLeaveRequest>
}