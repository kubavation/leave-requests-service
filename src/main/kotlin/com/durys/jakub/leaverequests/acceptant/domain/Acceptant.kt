package com.durys.jakub.leaverequests.acceptant.domain

import arrow.core.Either
import arrow.core.right
import com.durys.jakub.leaverequests.request.domain.flow.AcceptedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.RejectedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.RejectionReason
import com.durys.jakub.leaverequests.request.domain.flow.SentForAcceptationLeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.Acceptation
import com.durys.jakub.leaverequests.sharedkernel.acceptation.AcceptationLevel
import java.util.*

internal class Acceptant(private val id: AcceptantId, private val acceptationLevel: AcceptationLevel) {

    fun reject(request: SentForAcceptationLeaveRequest, reason: RejectionReason): RejectedLeaveRequest {
        return RejectedLeaveRequest(request.information(), reason)
    }

    fun approve(request: SentForAcceptationLeaveRequest, acceptable: Boolean): Either<SentForAcceptationLeaveRequest, AcceptedLeaveRequest> {
        //todo find if exists acceptant (if exists return sentForAcceptationLeaveRequest)
        val acceptantId = AcceptantId(UUID.randomUUID())

        if (acceptable) {
            return Either.Right(AcceptedLeaveRequest(request.information(), request.acceptation()))
        }

        return Either.Left(SentForAcceptationLeaveRequest(request.information(), request.acceptation().increment(acceptantId)))
    }

    fun id() = id
    fun level() = acceptationLevel

}