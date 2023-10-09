package com.durys.jakub.leaverequests.entitlements.domain

import reactor.core.publisher.Mono

internal interface LeaveEntitlementsRepository {
    fun load(id: LeaveEntitlementsId): Mono<LeaveEntitlements>
}