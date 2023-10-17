package com.durys.jakub.leaverequests.applicant.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.acceptant.domain.Acceptant
import com.durys.jakub.leaverequests.request.domain.flow.SentForAcceptationLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.SubmittedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.WorkingLeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal class Applicant(private val id: ApplicantId,
                         private val information: ApplicantInformation,
                         private val entitlements: LeaveEntitlements) {


    fun submit(request: WorkingLeaveRequest): Either<RuntimeException, SubmittedLeaveRequest> {

        return entitlements.valid(request.type, request.period, request.settlementType)
                .map {
                    SubmittedLeaveRequest(
                            LeaveRequestInformation(request.id, id, request.type, request.period, request.settlementType, request.alternateId)) }
    }

    fun sendForAcceptation(request: SubmittedLeaveRequest, acceptant: Acceptant): SentForAcceptationLeaveRequest {
        return request.sendForAcceptation(acceptant.id())
    }
}