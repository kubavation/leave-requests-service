package com.durys.jakub.leaverequests.applicant.domain

import java.math.BigDecimal

internal data class LeaveEntitlement(val id: LeaveEntitlementsId, val days: BigDecimal, val hours: BigDecimal) {

}