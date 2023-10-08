package com.durys.jakub.leaverequests.requests

import com.durys.jakub.leaverequests.request.domain.AuthorId
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.LeaveRequestType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.util.*

class LeaveRequestTest {

    @Test
    fun createLeaveRequest_shouldSuccessfullyCreateRequest() {
        assertDoesNotThrow {
            LeaveRequest(AuthorId(UUID.randomUUID()), LocalDate.now(), LocalDate.now().plusDays(1), LeaveRequestType.ANNUAL_LEAVE)
        }
    }
}