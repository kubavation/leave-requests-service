package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

internal class DailyPeriod(private val from: LocalDateTime, private val to: LocalDateTime): Period {

    //todo
    constructor(from: LocalDate, to: LocalDate, workingTimeSchedule: Any? = null): this(from.atStartOfDay(), to.atStartOfDay()) {
    }

    init {
        if (from.isAfter(to)) {
            throw InvalidLeaveRequestPeriod(from.toLocalDate(), to.toLocalDate())
        }
    }

    override fun amount(): BigDecimal = (java.time.Period.between(from.toLocalDate(), to.toLocalDate()).days + 1).toBigDecimal()

    override fun from() = from
    override fun to() = to

}
