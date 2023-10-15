package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.time.LocalDate
import java.time.LocalDateTime

internal class WorkingLeaveRequest(val id: LeaveRequestId, val type: LeaveRequestType,
                                   val from: LocalDateTime, val to: LocalDateTime, val alternateId: AlternateId? = null) {
}