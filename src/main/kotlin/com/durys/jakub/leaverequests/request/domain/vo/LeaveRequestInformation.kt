package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementType

internal open class LeaveRequestInformation(val id: LeaveRequestId, val applicantId: ApplicantId, val type: LeaveRequestType,
                                            val period: Period, val settlement: SettlementType, val alternateId: AlternateId? = null) {
}