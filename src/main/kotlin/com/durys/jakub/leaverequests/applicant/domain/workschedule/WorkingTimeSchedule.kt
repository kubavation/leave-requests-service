package com.durys.jakub.leaverequests.applicant.domain.workschedule

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.extra.math.sumAll
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

class WorkingTimeSchedule(val date: LocalDate, val from: LocalTime, val to: LocalTime, val workingDay: Boolean = true) {

    fun hours() = Duration.between(from, to).toHours()

    fun minutes() = Duration.between(from, to).toMinutes()

    companion object {

        fun calculate(schedules: Flux<WorkingTimeSchedule>): Mono<WorkingTimeScheduleAmount> {

            val days = schedules
                .filter { it.workingDay }
                .map { Pair(it.hours(), it.minutes()) }

            return days.sumAll { it.first }
                .zipWith(days.sumAll { it.second })
                .map { WorkingTimeScheduleAmount(it.t1.toBigDecimal(), it.t2.toBigDecimal()) }
        }

    }

}