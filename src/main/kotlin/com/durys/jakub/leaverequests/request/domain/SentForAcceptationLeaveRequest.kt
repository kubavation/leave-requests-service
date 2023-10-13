package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal class SentForAcceptationLeaveRequest(private val information: LeaveRequestInformation, private val applicantId: ApplicantId,
                                              val type: LeaveRequestType, val alternateId: AlternateId? = null): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}