package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeSchedule
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeScheduleRepository
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class PeriodFactoryTest {

    private val leaveRequestSettlementService: LeaveRequestSettlementService = Mockito.mock(LeaveRequestSettlementService::class.java)
    private val workingTimeScheduleRepository: WorkingTimeScheduleRepository = Mockito.mock(WorkingTimeScheduleRepository::class.java)

    private val periodFactory = PeriodFactory(workingTimeScheduleRepository, leaveRequestSettlementService)

    @Test
    fun createDailyPeriod_shouldSuccessfullyCreateDailyPeriod_whenAllDaysAreWorking() {

        val applicantId = ApplicantId(UUID.randomUUID())
        val requestType = LeaveRequestType.ANNUAL_LEAVE
        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 2)

        val daysExpected = BigDecimal.valueOf(2)
        val hoursExpected = BigDecimal.valueOf(15)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from, to)).then {
            Flux.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1),
                            LocalTime.of(8, 0), LocalTime.of(16, 0), true),
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 2),
                            LocalTime.of(8, 0), LocalTime.of(15, 0), true)
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(false)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, null, null))
                .consumeNextWith {
                    assertEquals(daysExpected, it.days())
                    assertEquals(hoursExpected, it.hours())
                    assertEquals(from, it.from())
                    assertEquals(to, it.to())
                }
                .verifyComplete()
    }

    @Test
    fun createDailyPeriod_shouldSuccessfullyCreateDailyPeriod_whenNotAllDaysAreWorking() {

        val applicantId = ApplicantId(UUID.randomUUID())
        val requestType = LeaveRequestType.ANNUAL_LEAVE
        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 3)

        val daysExpected = BigDecimal.valueOf(1)
        val hoursExpected = BigDecimal.valueOf(8)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from, to)).then {
            Flux.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1),
                            LocalTime.of(8, 0), LocalTime.of(16, 0), false),
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 2),
                            LocalTime.of(8, 0), LocalTime.of(15, 0), false),
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 3),
                            LocalTime.of(8, 0), LocalTime.of(16, 0), true)
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(false)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, null, null))
                .consumeNextWith {
                    assertEquals(daysExpected, it.days())
                    assertEquals(hoursExpected, it.hours())
                    assertEquals(from, it.from())
                    assertEquals(to, it.to())
                }
                .verifyComplete()
    }

    @Test
    fun createHourlyPeriod_shouldSuccessfullyCreateHourlyPeriod() {

        val applicantId = ApplicantId(UUID.randomUUID())
        val requestType = LeaveRequestType.ANNUAL_LEAVE
        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 1)
        val timeFrom = LocalTime.of(8, 0)
        val timeTo = LocalTime.of(10, 0)

        val daysExpected = BigDecimal.valueOf(1)
        val hoursExpected = BigDecimal.valueOf(2)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from, to)).then {
            Flux.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), false),
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(true)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, timeFrom, timeTo))
                .consumeNextWith {
                    assertEquals(daysExpected, it.days())
                    assertEquals(hoursExpected, it.hours())
                    assertEquals(from, it.from())
                    assertEquals(to, it.to())
                }
                .verifyComplete()
    }

}