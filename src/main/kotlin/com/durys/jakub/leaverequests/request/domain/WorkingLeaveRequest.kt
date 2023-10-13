package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal class WorkingLeaveRequest(val id: LeaveRequestId, val type: LeaveRequestType,
                          val settlement: Settlement, val alternateId: AlternateId? = null) {
}