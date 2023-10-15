package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeSchedule
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeScheduleRepository
import com.durys.jakub.leaverequests.request.domain.vo.Period
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate

@Component
internal class PeriodService(private val workingTimeScheduleRepository: WorkingTimeScheduleRepository) {


    fun period(applicantId: ApplicantId, from: LocalDate, to: LocalDate): Mono<Period> {

        //todo
        return Mono.just(null);
    }

}