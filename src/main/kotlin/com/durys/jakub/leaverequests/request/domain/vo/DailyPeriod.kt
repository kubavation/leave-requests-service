package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import java.math.BigDecimal
import java.time.LocalDate

internal class DailyPeriod(private val from: LocalDate, private val to: LocalDate, private val days: BigDecimal, private val hours: BigDecimal)
    : Period(from, to, null, null) {

    init {
        if (from.isAfter(to)) {
            throw InvalidLeaveRequestPeriod(from, to)
        }
    }

    override fun days() = days

    override fun hours() = hours

}
