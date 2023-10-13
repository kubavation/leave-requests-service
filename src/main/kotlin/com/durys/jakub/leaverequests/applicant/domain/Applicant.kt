package com.durys.jakub.leaverequests.applicant.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestInformation
import com.durys.jakub.leaverequests.request.domain.SentForAcceptationLeaveRequest
import com.durys.jakub.leaverequests.request.domain.SubmittedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.WorkingLeaveRequest
import java.util.*

internal class Applicant(private val id: ApplicantId) {


    fun submit(request: WorkingLeaveRequest): SubmittedLeaveRequest {
        return SubmittedLeaveRequest(LeaveRequestInformation(request.id, request.type, request.settlement), id, request.alternateId)
    }

    fun sentForAcceptation(request: SubmittedLeaveRequest): SentForAcceptationLeaveRequest {
        val acceptantId = AcceptantId(UUID.randomUUID()) //todo find acceptantId
        return request.sendForAcceptation(acceptantId)
    }
}