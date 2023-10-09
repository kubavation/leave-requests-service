package com.durys.jakub.leaverequests.entitlements.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.time.LocalDate

internal class LeaveEntitlementsId(private val applicantId: ApplicantId, private val absenceType: LeaveRequestType, private val at: LocalDate) {
}