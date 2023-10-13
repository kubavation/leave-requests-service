package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal class SentForAcceptationLeaveRequest(private val information: LeaveRequestInformation,
                                              private val applicantId: ApplicantId,
                                              val acceptantId: AcceptantId): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}