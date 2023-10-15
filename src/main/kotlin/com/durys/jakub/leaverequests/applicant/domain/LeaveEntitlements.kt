package com.durys.jakub.leaverequests.applicant.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period

internal data class LeaveEntitlements(val entitlements: List<LeaveEntitlement>) {

    fun valid(requestType: LeaveRequestType, period: Period): Either<RuntimeException, Unit?> {
        findEntitlement(requestType, period)
                ?.let {
                    if (it.days > period.amount()) { //TODO explare domain
                        return Either.Left(RuntimeException("Invalid number of days"));
                    }

                    return Either.Right(null)
                }

        return Either.Left(RuntimeException("Entitlements not specified")) //TODO
    }

    private fun findEntitlement(requestType: LeaveRequestType, period: Period): LeaveEntitlement? {
        return entitlements.find { it.requestType == requestType}
    }

}