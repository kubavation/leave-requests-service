package com.durys.jakub.leaverequests.acceptant.domain

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import reactor.core.publisher.Mono

internal interface AcceptantRepository {
    fun load(id: AcceptantId): Mono<Acceptant>
    fun load(request: LeaveRequest): Mono<Acceptant>
}