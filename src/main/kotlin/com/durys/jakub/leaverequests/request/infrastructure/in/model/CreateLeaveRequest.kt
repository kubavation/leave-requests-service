package com.durys.jakub.leaverequests.request.infrastructure.`in`.model

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.time.LocalDate

internal class CreateLeaveRequest(val applicantId: String, val from: LocalDate, val to: LocalDate, val type: LeaveRequestType,
                         val alternateId: String?) {
}