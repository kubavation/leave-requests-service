package com.durys.jakub.leaverequests.applicant.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.request.domain.LeaveRequest

internal data class LeaveEntitlements(val entitlements: List<LeaveEntitlement>) {

    fun valid(request: LeaveRequest): Either<RuntimeException, LeaveRequest> {

        findEntitlement(request)
                ?.let {
                    if (it.days > request.settlement().period.amount()) { //TODO explare domain
                        return Either.Left(RuntimeException("Invalid number of days"));
                    }

                    return Either.Right(request)
                }

        return Either.Left(RuntimeException("Entitlements not specified")) //TODO
    }

    private fun findEntitlement(request: LeaveRequest): LeaveEntitlement? {
        return entitlements.find { it.requestType == request.type()}
    }

}