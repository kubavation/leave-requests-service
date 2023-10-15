package com.durys.jakub.leaverequests.applicant.domain.workschedule

import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

class WorkingTimeSchedule(val date: LocalDate, val from: LocalTime, val to: LocalTime, val workingDay: Boolean = true) {

    fun hours() = Duration.between(from, to).toHours()

    fun minutes() = Duration.between(from, to).toMinutes()

    companion object {

        fun calculate(schedules: List<WorkingTimeSchedule>): WorkingTimeScheduleAmount {

            val amount = schedules
                .filter { it.workingDay }
                .map { Pair(it.hours(), it.minutes()) }
                .toList()

            return WorkingTimeScheduleAmount(amount.sumOf { BigDecimal.valueOf(it.first) }, amount.sumOf { BigDecimal.valueOf(it.second) })
        }

    }

}