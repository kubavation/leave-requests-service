package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId

internal class SubmittedLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

    fun sendForAcceptation(acceptantId: AcceptantId): SentForAcceptationLeaveRequest {
        return SentForAcceptationLeaveRequest(information, acceptantId)
    }

}