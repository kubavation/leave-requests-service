package com.durys.jakub.leaverequests.request.infrastructure.`in`.model

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.time.LocalDate
import java.time.LocalTime

internal class CreateLeaveRequest(val applicantId: String, val from: LocalDate, val to: LocalDate,
                                  val timeFrom: LocalTime?, val timeTo: LocalTime?,
                                  val type: LeaveRequestType, val alternateId: String?) {
}