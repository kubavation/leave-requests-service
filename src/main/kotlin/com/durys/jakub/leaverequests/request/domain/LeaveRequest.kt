package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period

internal data class LeaveRequest(val applicantId: ApplicantId, val period: Period, val type: LeaveRequestType) {
}