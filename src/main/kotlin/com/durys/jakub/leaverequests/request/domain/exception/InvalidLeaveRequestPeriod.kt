package com.durys.jakub.leaverequests.request.domain.exception

import java.time.LocalDate

class InvalidLeaveRequestPeriod(val from: LocalDate, val to: LocalDate) : RuntimeException("Invalid period $from - $to") {
}