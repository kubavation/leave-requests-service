package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.Settlement

internal open class LeaveRequestInformation(val id: LeaveRequestId, val applicantId: ApplicantId, val type: LeaveRequestType,
                                            val settlement: Settlement, val alternateId: AlternateId? = null) {
}