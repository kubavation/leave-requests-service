package com.durys.jakub.leaverequests.entitlements.domain

internal interface LeaveEntitlementsRepository {
    fun load(id: LeaveEntitlementsId): LeaveEntitlements
}