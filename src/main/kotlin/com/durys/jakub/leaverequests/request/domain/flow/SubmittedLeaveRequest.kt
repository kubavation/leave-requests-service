package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.Acceptation
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal class SubmittedLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

    override fun acceptation(): Acceptation {
        TODO("Not yet implemented")
    }

    fun sendForAcceptation(acceptantId: AcceptantId): SentForAcceptationLeaveRequest {
        return SentForAcceptationLeaveRequest(information, acceptantId)
    }

}