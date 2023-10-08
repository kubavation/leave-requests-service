package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import java.time.LocalDate
import java.time.Period

internal class Period(val from: LocalDate, val to: LocalDate) {

    init {
        if (from.isAfter(to)) {
            throw InvalidLeaveRequestPeriod(from, to)
        }
    }


    fun days() = Period.between(from, to).days + 1

}
