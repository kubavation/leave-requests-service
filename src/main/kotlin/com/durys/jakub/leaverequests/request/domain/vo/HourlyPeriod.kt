package com.durys.jakub.leaverequests.request.domain.vo

import java.math.BigDecimal
import java.time.LocalDateTime

internal class HourlyPeriod(private val from: LocalDateTime, private val to: LocalDateTime): Period<LocalDateTime> {


    override fun amount(): BigDecimal = (java.time.Duration.between(from, to).toHours() + 1).toBigDecimal()

    override fun from() = from

    override fun to() = to

}
