package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period

internal class LeaveRequest(private val applicantId: ApplicantId, private val period: Period, private val type: LeaveRequestType) {
}