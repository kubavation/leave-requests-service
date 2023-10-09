package com.durys.jakub.leaverequests.entitlements.infrastructure

import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlements
import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlementsId
import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlementsRepository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

internal class RestLeaveEntitlementsRepository(private val client: WebClient): LeaveEntitlementsRepository {

    override fun load(id: LeaveEntitlementsId): Mono<LeaveEntitlements> {
        return Mono.empty(); //TODO
    }
}