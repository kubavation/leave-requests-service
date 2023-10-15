package com.durys.jakub.leaverequests.applicant.domain.workschedule

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

class WorkingTimeSchedule(val date: LocalDate, val from: LocalTime, val to: LocalTime, val workingDay: Boolean = true) {

    fun hours() = Duration.between(from, to).toHours()

    fun minutes() = Duration.between(from, to).toMinutes()

}