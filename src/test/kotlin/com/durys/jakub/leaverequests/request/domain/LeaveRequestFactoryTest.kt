package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeSchedule
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementRepository
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementType
import com.durys.jakub.leaverequests.request.domain.vo.DailyPeriod
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.sharedkernel.identityprovider.IdentityProvider
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

internal class LeaveRequestFactoryTest {

    private val identityProvider = Mockito.mock(IdentityProvider::class.java)
    private val periodFactory = Mockito.mock(PeriodFactory::class.java)
    private val settlementRepository = Mockito.mock(SettlementRepository::class.java)

    private val leaveRequestFactory = LeaveRequestFactory(identityProvider, periodFactory, settlementRepository)


    //
    private val applicantId = ApplicantId(UUID.randomUUID())
    private val leaveRequestType = LeaveRequestType.ANNUAL_LEAVE


    @Test
    fun createLeaveRequest_shouldSuccessfullyCreateRequestWithDailyPeriod() {

        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 3)

        val expectedDays = 3L
        val expectedHours = 24L

        Mockito.`when`(identityProvider.next()).then { UUID.randomUUID() }

        Mockito.`when`(periodFactory.period(applicantId, leaveRequestType, from, to, null, null))
                .then { Mono.just(DailyPeriod(from, to, BigDecimal.valueOf(expectedDays), BigDecimal.valueOf(expectedHours))) }

        Mockito.`when`(settlementRepository.resolve(applicantId, leaveRequestType, to))
                .then { Mono.just(SettlementType.DAILY) }


        StepVerifier
                .create(leaveRequestFactory.build(applicantId, leaveRequestType, from, to, null, null, null))
                .consumeNextWith {
                    assertEquals(expectedDays.toBigDecimal(), it.period.days())
                    assertEquals(expectedHours.toBigDecimal(), it.period.hours())
                    assertTrue(it.settlementType == SettlementType.DAILY)
                    assertTrue(it.period is DailyPeriod)
                }
                .verifyComplete()

    }


}