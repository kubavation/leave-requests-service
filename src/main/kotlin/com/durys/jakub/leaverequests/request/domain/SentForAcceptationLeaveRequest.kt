package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId

internal class SentForAcceptationLeaveRequest(private val information: LeaveRequestInformation, private val applicantId: ApplicantId): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}