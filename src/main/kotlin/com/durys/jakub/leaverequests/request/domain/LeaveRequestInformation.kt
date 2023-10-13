package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType

internal open class LeaveRequestInformation(val id: LeaveRequestId, val type: LeaveRequestType, val settlement: Settlement) {
}