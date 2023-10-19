package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.applicant.domain.*
import com.durys.jakub.leaverequests.applicant.domain.Applicant
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.ApplicantInformation
import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.request.domain.LeaveRequestFactory
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.SubmitLeaveRequestCommand
import com.durys.jakub.leaverequests.request.domain.flow.WorkingLeaveRequest
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementType
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.DailyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

class SubmitLeaveRequestCommandHandlerTest {
    private val leaveRequestRepository = Mockito.mock(LeaveRequestRepository::class.java)
    private val applicantRepository = Mockito.mock(ApplicantRepository::class.java)
    private val leaveRequestFactory = Mockito.mock(LeaveRequestFactory::class.java)

    private val submitLeaveRequestCommandHandler
         = SubmitLeaveRequestCommandHandler(leaveRequestRepository, applicantRepository, leaveRequestFactory)


    @Test
    fun submitLeaveRequest_shouldSuccessfullyCreateLeaveRequest() {

        val command = SubmitLeaveRequestCommand(
                ApplicantId(UUID.randomUUID()), LeaveRequestType.ANNUAL_LEAVE,
                LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1),
                null, null, null)

        val entitlements = LeaveEntitlements(
            listOf(
                LeaveEntitlement(
                        LeaveEntitlementsId(command.applicantId, command.leaveRequestType, LocalDate.now()),
                        command.leaveRequestType, LocalDate.of(2023,1,1),
                        LocalDate.of(2023,12,31), BigDecimal.valueOf(26), BigDecimal.valueOf(170)),
                LeaveEntitlement(
                        LeaveEntitlementsId(command.applicantId, command.leaveRequestType, LocalDate.now()),
                        command.leaveRequestType, LocalDate.of(2022,1,1),
                        LocalDate.of(2022,12,31), BigDecimal.valueOf(26), BigDecimal.valueOf(170))
            )
        )

        Mockito.`when`(
                leaveRequestFactory.build(command.applicantId, command.leaveRequestType,  command.from,
                        command.to, command.timeFrom, command.timeTo, command.alternateId)).then {
            Mono.just(
                WorkingLeaveRequest(LeaveRequestId(UUID.randomUUID()), command.leaveRequestType,
                        DailyPeriod(command.from, command.to, BigDecimal.valueOf(1), BigDecimal.valueOf(8)), SettlementType.DAILY, null)
            )
        }

        Mockito.`when`(applicantRepository.load(command.applicantId)).then {
            Mono.just(
                    Applicant(command.applicantId,
                            ApplicantInformation("John Doe", LocalDate.of(2021,1,1), null),
                            entitlements)
            )
        }

        StepVerifier
                .create(submitLeaveRequestCommandHandler.handle(command))
                .consumeNextWith {
                    assertTrue(OperationResult.success().status === it.status)
                }
                .verifyComplete()

    }
}