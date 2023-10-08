package com.durys.jakub.leaverequests.request.domain

internal class LeaveRequest(val authorId: AuthorId, val period: Period, val type: LeaveRequestType) {
}