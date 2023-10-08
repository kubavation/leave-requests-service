package com.durys.jakub.leaverequests.request.domain

internal class LeaveRequest(private val authorId: AuthorId, private val period: Period, private val type: LeaveRequestType) {
}