package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.exception.InvalidLeaveRequestPeriod
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PeriodTest {


    @Test
    fun createLeaveRequestPeriod_shouldReturnValidNumberOfDays() {

        val from = LocalDate.of(2023, 1, 1);
        val to = LocalDate.of(2023, 1, 3);

        assertEquals(3,  Period(from, to).days())
    }


    @Test
    fun createLeaveRequestPeriod_shouldThrowExceptionWhenDatesAreInvalid() {

        val from = LocalDate.of(2023, 1, 3);
        val to = LocalDate.of(2023, 1, 1);

        assertThrows(InvalidLeaveRequestPeriod::class.java) {
            Period(from, to)
        }
    }

}