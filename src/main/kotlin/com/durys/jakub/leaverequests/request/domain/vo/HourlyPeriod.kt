package com.durys.jakub.leaverequests.request.domain.vo

import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

internal class HourlyPeriod(private val at: LocalDate, private val from: LocalTime, private val to: LocalTime)
    : Period(at, at, from, to) {

    override fun days() = BigDecimal.ONE

    override fun hours() = Duration.between(from, to).toHours().toBigDecimal()


}
