package com.durys.jakub.leaverequests.request.domain

import java.time.LocalDate

internal class LeaveRequest(val authorId: AuthorId, val from: LocalDate, val to: LocalDate, val type: LeaveRequestType) {
}