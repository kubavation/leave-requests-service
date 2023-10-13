package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.time.LocalDate

internal class WorkingLeaveRequest(val id: LeaveRequestId, val type: LeaveRequestType,
                          val from: LocalDate, val to: LocalDate, val alternateId: AlternateId? = null) {
}