package com.durys.jakub.leaverequests.applicant.infrastructure.workschedule

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeSchedule
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeScheduleRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalTime

@Component
internal class RestWorkingTimeScheduleRepository: WorkingTimeScheduleRepository {

    override fun workingTimeSchedule(applicantId: ApplicantId, from: LocalDate, to: LocalDate): Flux<WorkingTimeSchedule> {
         return Flux.just(WorkingTimeSchedule(LocalDate.now(), LocalTime.now(), LocalTime.now())) //todo
    }

    override fun workingTimeSchedule(applicantId: ApplicantId, at: LocalDate): Mono<WorkingTimeSchedule> {
        return workingTimeSchedule(applicantId, at, at).single()
    }

}