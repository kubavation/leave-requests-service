package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal open class LeaveRequestInformation(val id: LeaveRequestId, val applicantId: ApplicantId, val type: LeaveRequestType,
                                            val settlement: Settlement, val alternateId: AlternateId? = null) {
}