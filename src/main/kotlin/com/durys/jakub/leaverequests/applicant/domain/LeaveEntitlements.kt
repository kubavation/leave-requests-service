package com.durys.jakub.leaverequests.applicant.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import java.math.BigDecimal

internal data class LeaveEntitlements(val id: LeaveEntitlementsId, val days: BigDecimal, val hours: BigDecimal) {

    fun valid(request: LeaveRequest): Either<RuntimeException, LeaveRequest> {
        if (days > request.settlement.period.amount()) { //todo explare domain
            return Either.Left(RuntimeException("Invalid number of days"));
        }
        return Either.Right(request)
    }

}