package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import java.time.LocalDate
import java.time.Period

internal class Period(private val from: LocalDate, private val to: LocalDate) {

    init {
        if (from.isAfter(to)) {
            throw InvalidLeaveRequestPeriod(from, to)
        }
    }


    fun days() = Period.between(from, to).days + 1

}
