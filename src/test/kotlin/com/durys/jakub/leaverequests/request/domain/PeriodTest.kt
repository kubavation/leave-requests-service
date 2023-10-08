package com.durys.jakub.leaverequests.request.domain

import jdk.jfr.Period
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PeriodTest {


    @Test
    fun createLeaveRequestPeriod_shouldReturnValidNumberOfDays() {

        val from = LocalDate.of(2023, 1, 1);
        val to = LocalDate.of(2023, 1, 3);

        assertEquals(3,  Period(from, to).days())

    }
}