package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal data class LeaveRequest(val requestId: LeaveRequestId, val applicantId: ApplicantId, val settlement: Settlement,
                                 val type: LeaveRequestType, val alternateId: AlternateId? = null) {

    constructor(requestId: LeaveRequestId, applicantId: ApplicantId, settlement: Settlement,
                type: LeaveRequestType, alternateId: AlternateId?, status: Status)
                        : this(requestId, applicantId, settlement, type, alternateId) {
        this.status = status
    }

    private var status: Status = Status.NEW

}