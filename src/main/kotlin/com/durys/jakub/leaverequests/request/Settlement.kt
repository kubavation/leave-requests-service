package com.durys.jakub.leaverequests.request

import com.durys.jakub.leaverequests.request.domain.vo.DailyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.HourlyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.Period
import java.time.LocalDate
import java.time.LocalDateTime

class Settlement(val period: Period) {

    companion object {
        fun hourly(from: LocalDateTime, to: LocalDateTime) = Settlement(HourlyPeriod(from, to))
        fun daily(from: LocalDate, to: LocalDate) = Settlement(DailyPeriod(from, to))
    }

}