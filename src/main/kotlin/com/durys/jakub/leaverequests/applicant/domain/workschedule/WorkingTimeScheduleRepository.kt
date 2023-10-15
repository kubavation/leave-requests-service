package com.durys.jakub.leaverequests.applicant.domain.workschedule

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import reactor.core.publisher.Flux
import java.time.LocalDate

internal interface WorkingTimeScheduleRepository {
    fun workingTimeSchedule(applicantId: ApplicantId, from: LocalDate, to: LocalDate): Flux<WorkingTimeSchedule>
}