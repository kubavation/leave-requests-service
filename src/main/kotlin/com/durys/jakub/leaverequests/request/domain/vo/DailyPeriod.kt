package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.common.exception.ValidationExceptionHandler
import com.durys.jakub.leaverequests.common.exception.ValidationHandlers
import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import java.math.BigDecimal
import java.time.LocalDate

internal class DailyPeriod(private val from: LocalDate, private val to: LocalDate, private val days: BigDecimal, private val hours: BigDecimal)
    : Period(from, to, null, null) {

    init {
        test(from, to, hours, days, ValidationHandlers.throwingValidationExceptionHandler())
    }

    override fun days() = days

    override fun hours() = hours


    companion object {
        fun test(from: LocalDate, to: LocalDate, days: BigDecimal, hours: BigDecimal, handler: ValidationExceptionHandler) {

            if (from.isAfter(to)) {
                handler.handle(InvalidLeaveRequestPeriod(from, to))
            }

            if (days == BigDecimal.ZERO || hours == BigDecimal.ZERO) {
                handler.handle(RuntimeException("Invalid period based of working time schedule"))
            }
        }
    }

}
