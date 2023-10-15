package com.durys.jakub.leaverequests.request.domain.vo

import java.math.BigDecimal

interface Period<T> {
    fun amount(): BigDecimal
    fun from(): T
    fun to(): T
}