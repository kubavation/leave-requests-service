package com.durys.jakub.leaverequests.request.domain.exception

import java.time.LocalDate

class InvalidLeaveRequestPeriod(from: LocalDate, to: LocalDate)
    : RuntimeException("Invalid period $from - $to")
