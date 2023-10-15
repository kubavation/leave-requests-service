package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.DailyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.HourlyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.Period
import java.time.LocalDateTime

class Settlement(private val period: Period) {

    companion object {
        fun hourly(from: LocalDateTime, to: LocalDateTime) = Settlement(HourlyPeriod(from, to))
        fun daily(from: LocalDateTime, to: LocalDateTime) = Settlement(DailyPeriod(from, to))
    }

    fun from(): LocalDateTime = period.from()
    fun to(): LocalDateTime = period.to()

}