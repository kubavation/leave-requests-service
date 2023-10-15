package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import java.math.BigDecimal
import java.time.LocalDate

internal class DailyPeriod(private val from: LocalDate, private val to: LocalDate): Period<LocalDate> {

    init {
        if (from.isAfter(to)) {
            throw InvalidLeaveRequestPeriod(from, to)
        }
    }


    override fun amount(): BigDecimal = (java.time.Period.between(from, to).days + 1).toBigDecimal()

    override fun from() = from
    override fun to() = to

}
