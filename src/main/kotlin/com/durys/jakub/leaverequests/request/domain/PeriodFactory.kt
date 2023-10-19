package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeSchedule
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeScheduleRepository
import com.durys.jakub.leaverequests.request.domain.vo.DailyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.HourlyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalTime

@Component
internal class PeriodFactory(
        private val workingTimeScheduleRepository: WorkingTimeScheduleRepository,
        private val leaveRequestService: LeaveRequestSettlementService) {


    fun period(applicantId: ApplicantId, requestType: LeaveRequestType, from: LocalDate, to: LocalDate,
               timeFrom: LocalTime?, timeTo: LocalTime?): Mono<Period> {

        return leaveRequestService.hoursDefinitionRequired(applicantId, requestType, to)
                .flatMap { resolve(applicantId, from, to, it, timeFrom, timeTo) }
    }

    private fun resolve(applicantId: ApplicantId, from: LocalDate, to: LocalDate, hoursDefinitionRequired: Boolean,
                        timeFrom: LocalTime?, timeTo: LocalTime?): Mono<Period> {

        if (hoursDefinitionRequired) {
            return workingTimeScheduleRepository.workingTimeSchedule(applicantId, from)
                    .map { HourlyPeriod(from, timeFrom!!, timeTo!!, it) }
        }

        return WorkingTimeSchedule.calculate(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from, to))
                .map { DailyPeriod(from, to, it.days, it.hours) }
    }


}