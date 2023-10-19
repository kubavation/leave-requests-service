package com.durys.jakub.leaverequests.applicant.domain.workschedule

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.extra.math.sumAll
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

class WorkingTimeSchedule(val date: LocalDate, val from: LocalTime, val to: LocalTime, val workingDay: Boolean = true) {

    fun hours() = Duration.between(from, to).toHours()

    companion object {

        fun calculate(schedules: Flux<WorkingTimeSchedule>): Mono<WorkingTimeScheduleAmount> {
            return schedules.filter { it.workingDay }.count()
                .zipWith(
                    schedules
                        .filter { it.workingDay }
                        .map { it.hours() }
                        .sumAll { it })
                .map { WorkingTimeScheduleAmount(it.t1.toBigDecimal(), it.t2.toBigDecimal()) }
                .switchIfEmpty { Mono.just(WorkingTimeScheduleAmount(BigDecimal.ZERO, BigDecimal.ZERO)) }
        }

    }

}