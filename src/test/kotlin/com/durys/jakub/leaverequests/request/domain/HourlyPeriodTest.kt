package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeSchedule
import com.durys.jakub.leaverequests.applicant.domain.workschedule.WorkingTimeScheduleRepository
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

internal class HourlyPeriodTest {

    private val leaveRequestSettlementService: LeaveRequestSettlementService = Mockito.mock(LeaveRequestSettlementService::class.java)
    private val workingTimeScheduleRepository: WorkingTimeScheduleRepository = Mockito.mock(WorkingTimeScheduleRepository::class.java)

    private val periodFactory = PeriodFactory(workingTimeScheduleRepository, leaveRequestSettlementService)


    private val applicantId = ApplicantId(UUID.randomUUID())
    private val requestType = LeaveRequestType.ANNUAL_LEAVE

    @Test
    fun createHourlyPeriod_shouldSuccessfullyCreateHourlyPeriod() {

        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 1)
        val timeFrom = LocalTime.of(8, 0)
        val timeTo = LocalTime.of(10, 0)

        val daysExpected = BigDecimal.valueOf(1)
        val hoursExpected = BigDecimal.valueOf(2)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from)).then {
            Mono.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), true),
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

    @Test
    fun createHourlyPeriod_shouldThrowException_whenTimeFromToAreTheSame() {

        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 1)
        val timeFrom = LocalTime.of(8, 0)
        val timeTo = LocalTime.of(8, 0)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from)).then {
            Mono.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), false),
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(true)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, timeFrom, timeTo))
                .expectErrorMessage("Time from/to in cannot be the same")
                .verify()
    }

    @Test
    fun createHourlyPeriod_shouldThrowException_whenTimeFromIsAfterTimeTo() {

        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 1)
        val timeFrom = LocalTime.of(8, 0)
        val timeTo = LocalTime.of(7, 0)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from)).then {
            Mono.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), false),
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(true)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, timeFrom, timeTo))
                .expectErrorMessage("Time from cannot be later than time to")
                .verify()
    }


    @Test
    fun createHourlyPeriod_shouldThrowException_whenDefinedPeriodIsInDayOff() {

        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 1)
        val timeFrom = LocalTime.of(8, 0)
        val timeTo = LocalTime.of(9, 0)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from)).then {
            Mono.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), false),
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(true)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, timeFrom, timeTo))
                .expectErrorMessage("Invalid period based on working schedule")
                .verify()
    }



    @Test
    fun createHourlyPeriod_shouldThrowException_whenDefinedPeriodIsNotInWorkingSchedule() {

        val from = LocalDate.of(2023, 1, 1)
        val to = LocalDate.of(2023, 1, 1)
        val timeFrom = LocalTime.of(7, 0)
        val timeTo = LocalTime.of(10, 0)

        Mockito.`when`(workingTimeScheduleRepository.workingTimeSchedule(applicantId, from)).then {
            Mono.just(
                    WorkingTimeSchedule(LocalDate.of(2023, 1, 1), LocalTime.of(8, 0), LocalTime.of(16, 0), false),
            )
        }

        Mockito.`when`(leaveRequestSettlementService.hoursDefinitionRequired(applicantId, requestType, to)).then {
            Mono.just(true)
        }


        StepVerifier
                .create(periodFactory.period(applicantId, requestType, from, to, timeFrom, timeTo))
                .expectErrorMessage("Invalid period based on working schedule")
                .verify()
    }

}