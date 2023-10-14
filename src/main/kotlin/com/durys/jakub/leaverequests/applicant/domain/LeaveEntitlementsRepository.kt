package com.durys.jakub.leaverequests.applicant.domain

import reactor.core.publisher.Mono

internal interface LeaveEntitlementsRepository {
    fun load(id: LeaveEntitlementsId): Mono<LeaveEntitlement>
}