package com.durys.jakub.leaverequests.request.domain.command

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.cqrs.command.Command
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import java.time.LocalDate
import java.time.LocalTime

internal class SubmitLeaveRequestCommand(val applicantId: ApplicantId, val leaveRequestType: LeaveRequestType,
                                val from: LocalDate, val to: LocalDate, val timeFrom: LocalTime?, val timeTo: LocalTime?,
                                val alternateId: AlternateId?): Command {
}