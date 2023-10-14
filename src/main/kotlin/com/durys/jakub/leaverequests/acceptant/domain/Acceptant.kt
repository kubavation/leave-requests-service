package com.durys.jakub.leaverequests.acceptant.domain

import arrow.core.Either
import com.durys.jakub.leaverequests.request.domain.flow.AcceptedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.RejectedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.RejectionReason
import com.durys.jakub.leaverequests.request.domain.flow.SentForAcceptationLeaveRequest
import java.util.*

internal class Acceptant(private val id: AcceptantId) {

    fun reject(request: SentForAcceptationLeaveRequest, reason: RejectionReason): RejectedLeaveRequest {
        return RejectedLeaveRequest(request.information(), reason)
    }

    fun approve(request: SentForAcceptationLeaveRequest): Either<SentForAcceptationLeaveRequest, AcceptedLeaveRequest> {
        //todo find if exists acceptant (if exists return sentForAcceptationLeaveRequest)
        val acceptantId = AcceptantId(UUID.randomUUID())

        return Either.Right(AcceptedLeaveRequest(request.information()))
    }

}