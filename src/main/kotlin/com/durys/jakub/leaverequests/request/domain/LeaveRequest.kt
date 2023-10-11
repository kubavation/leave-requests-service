package com.durys.jakub.leaverequests.request.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlements
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal data class LeaveRequest(val requestId: LeaveRequestId, val applicantId: ApplicantId, val settlement: Settlement,
                                 val type: LeaveRequestType, val alternateId: AlternateId? = null) {


    fun validWith(entitlements: LeaveEntitlements): Either<RuntimeException, LeaveRequest> {
        if (entitlements.days > settlement.period.amount()) { //todo explare domain
            return Either.Left(RuntimeException("Invalid number of days"));
        }
        return Either.Right(this)
    }
}