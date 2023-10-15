package com.durys.jakub.leaverequests.request.domain.vo

import java.math.BigDecimal
import java.time.LocalDateTime

interface Period {
    fun amount(): BigDecimal
    fun from(): LocalDateTime
    fun to(): LocalDateTime
}