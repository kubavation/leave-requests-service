package com.durys.jakub.leaverequests.applicant.domain

import com.durys.jakub.leaverequests.applicant.domain.LeaveEntitlements
import com.durys.jakub.leaverequests.applicant.domain.LeaveEntitlementsId
import reactor.core.publisher.Mono

internal interface LeaveEntitlementsRepository {
    fun load(id: LeaveEntitlementsId): Mono<LeaveEntitlements>
}