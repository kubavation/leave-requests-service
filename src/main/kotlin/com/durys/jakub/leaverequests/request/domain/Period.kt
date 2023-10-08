package com.durys.jakub.leaverequests.request.domain

import java.time.LocalDate
import java.time.Period

internal class Period(val from: LocalDate, val to: LocalDate) {


    fun days() = Period.between(from, to).days + 1

}
