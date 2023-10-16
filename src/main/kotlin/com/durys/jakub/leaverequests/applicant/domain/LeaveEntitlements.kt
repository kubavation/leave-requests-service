package com.durys.jakub.leaverequests.applicant.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementType
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period

internal data class LeaveEntitlements(val entitlements: List<LeaveEntitlement>) {

    fun valid(requestType: LeaveRequestType, period: Period, settlement: SettlementType): Either<RuntimeException, Unit?> {
        findEntitlement(requestType, period)
                ?.let {

                    if (settlement === SettlementType.DAILY && it.days > settlement.amountResolver.invoke(period)) {
                        return Either.Left(RuntimeException("Invalid number of days"));
                    }

                    if (settlement === SettlementType.HOURLY && it.hours > settlement.amountResolver.invoke(period)) {
                        return Either.Left(RuntimeException("Invalid number of days"));
                    }

                    return Either.Right(null)
                }

        return Either.Left(RuntimeException("Entitlements not specified")) //TODO
    }

    private fun findEntitlement(requestType: LeaveRequestType, period: Period): LeaveEntitlement? {
        return entitlements.find { it.requestType == requestType && (!period.to().isBefore(it.from) && !period.to().isAfter(it.to))}
    }

}