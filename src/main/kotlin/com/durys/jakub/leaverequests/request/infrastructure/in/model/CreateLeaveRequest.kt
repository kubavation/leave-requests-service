package com.durys.jakub.leaverequests.request.infrastructure.`in`.model

import java.time.LocalDate

class CreateLeaveRequest(val applicantId: String, val from: LocalDate, val to: LocalDate, val alternateId: String?) {
}