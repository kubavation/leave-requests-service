package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.util.*

class LeaveRequestTest {

    @Test
    fun createLeaveRequest_shouldSuccessfullyCreateRequest() {
        assertDoesNotThrow {
            LeaveRequest(ApplicantId(UUID.randomUUID()), Period(LocalDate.now(), LocalDate.now().plusDays(1)), LeaveRequestType.ANNUAL_LEAVE)
        }
    }
}