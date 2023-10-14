package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId

internal class SentForAcceptationLeaveRequest(private val information: LeaveRequestInformation, private val acceptantId: AcceptantId): LeaveRequest {

    fun reject(reason: RejectionReason): RejectedLeaveRequest {
        return RejectedLeaveRequest(information, reason)
    }


    override fun information(): LeaveRequestInformation = information

}