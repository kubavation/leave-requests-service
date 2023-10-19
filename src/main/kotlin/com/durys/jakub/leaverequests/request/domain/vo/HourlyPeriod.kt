package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.common.exception.ValidationExceptionHandler
import com.durys.jakub.leaverequests.common.exception.ValidationHandlers
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

internal class HourlyPeriod(private val at: LocalDate, private val from: LocalTime, private val to: LocalTime)
    : Period(at, at, from, to) {


    init {
        test(from, to, ValidationHandlers.throwingValidationExceptionHandler())
    }

    override fun days() = BigDecimal.ONE

    override fun hours() = Duration.between(from, to).toHours().toBigDecimal()


    companion object {

        fun test(from: LocalTime, to: LocalTime, handler: ValidationExceptionHandler) {

            if (from == to) {
                handler.handle(RuntimeException("Time from/to in cannot be the same"))
            }

            if (from.isAfter(to)) {
                handler.handle(RuntimeException("Time from cannot be later than time to"))
            }

        }

    }

}
