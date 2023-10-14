package com.durys.jakub.leaverequests.applicant.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.request.domain.*
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation
import com.durys.jakub.leaverequests.request.domain.flow.SentForAcceptationLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.SubmittedLeaveRequest
import com.durys.jakub.leaverequests.request.domain.flow.WorkingLeaveRequest
import java.util.*

internal class Applicant(private val id: ApplicantId) {


    fun submit(request: WorkingLeaveRequest): SubmittedLeaveRequest {
        //todo settlement factory
        return SubmittedLeaveRequest(LeaveRequestInformation(request.id, id, request.type, Settlement.daily(request.from, request.to), request.alternateId))
    }

    fun sendForAcceptation(request: SubmittedLeaveRequest): SentForAcceptationLeaveRequest {
        val acceptantId = AcceptantId(UUID.randomUUID()) //todo find acceptantId
        return request.sendForAcceptation(acceptantId)
    }
}