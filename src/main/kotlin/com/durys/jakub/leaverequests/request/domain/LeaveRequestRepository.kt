package com.durys.jakub.leaverequests.request.domain

internal interface LeaveRequestRepository {
    fun load(id: LeaveRequestId): LeaveRequest?
    fun save(leaveRequest: LeaveRequest)
}