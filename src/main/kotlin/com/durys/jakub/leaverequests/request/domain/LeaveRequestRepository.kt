package com.durys.jakub.leaverequests.request.domain

import reactor.core.publisher.Mono

internal interface LeaveRequestRepository {
    fun load(id: LeaveRequestId): Mono<LeaveRequest>
    fun save(leaveRequest: LeaveRequest)
}