package com.durys.jakub.leaverequests.request.domain.exception

import java.time.LocalDate

class InvalidLeaveRequestPeriod(private val from: LocalDate, private val to: LocalDate)
    : RuntimeException("Invalid period $from - $to")
