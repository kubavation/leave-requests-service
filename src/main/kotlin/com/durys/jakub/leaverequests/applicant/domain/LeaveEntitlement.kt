package com.durys.jakub.leaverequests.applicant.domain

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.math.BigDecimal
import java.time.LocalDate

internal data class LeaveEntitlement(val id: LeaveEntitlementsId, val requestType: LeaveRequestType,
                                     val from: LocalDate, val to: LocalDate, val days: BigDecimal, val hours: BigDecimal) {

}