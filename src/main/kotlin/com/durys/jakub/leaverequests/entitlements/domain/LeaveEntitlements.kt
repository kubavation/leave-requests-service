package com.durys.jakub.leaverequests.entitlements.domain

import java.math.BigDecimal

internal data class LeaveEntitlements(val id: LeaveEntitlementsId, val days: BigDecimal, val hours: BigDecimal) {


}