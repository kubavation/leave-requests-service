package com.durys.jakub.leaverequests.request.domain.vo

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

internal abstract class Period(private val dateFrom: LocalDate, private val dateTo: LocalDate,
                               private val timeFrom: LocalTime?, private val timeTo: LocalTime?) {

    abstract fun days(): BigDecimal
    abstract fun hours(): BigDecimal


    fun from() = dateFrom
    fun to() = dateTo
    fun timeFrom() = timeFrom
    fun timeTo() = timeTo
}