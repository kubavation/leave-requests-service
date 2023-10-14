package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId

internal class SentForAcceptationLeaveRequest(private val information: LeaveRequestInformation, private val acceptantId: AcceptantId): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}