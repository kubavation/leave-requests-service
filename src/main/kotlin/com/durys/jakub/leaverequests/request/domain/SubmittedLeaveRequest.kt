package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId

internal class SubmittedLeaveRequest(private val information: LeaveRequestInformation, private val applicantId: ApplicantId): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

    fun sendForAcceptation(acceptantId: AcceptantId): SentForAcceptationLeaveRequest {
        return SentForAcceptationLeaveRequest(information, applicantId, acceptantId)
    }

}