package com.durys.jakub.leaverequests.request.domain.settlement

import com.durys.jakub.leaverequests.request.domain.vo.Period
import java.math.BigDecimal

internal enum class SettlementType(private val amountResolver: (Period) -> BigDecimal) {
    DAILY({period -> period.days()}),
    HOURLY({period -> period.hours()});

}