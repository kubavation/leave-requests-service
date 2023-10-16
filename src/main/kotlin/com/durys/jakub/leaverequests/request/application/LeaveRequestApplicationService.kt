package com.durys.jakub.leaverequests.request.application

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.applicant.domain.LeaveEntitlementsId
import com.durys.jakub.leaverequests.applicant.domain.LeaveEntitlementsRepository
import com.durys.jakub.leaverequests.request.domain.LeaveRequestFactory
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.PeriodFactory
import com.durys.jakub.leaverequests.request.domain.flow.WorkingLeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.sharedkernel.identityprovider.IdentityProvider
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalTime

@Component
internal class LeaveRequestApplicationService(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val applicantRepository: ApplicantRepository,
        private val leaveRequestFactory: LeaveRequestFactory) {

    internal fun submit(applicantId: ApplicantId, leaveRequestType: LeaveRequestType,
                        from: LocalDate, to: LocalDate, timeFrom: LocalTime?, timeTo: LocalTime?,
                        alternateId: AlternateId?): Mono<OperationResult> { //todo refactor to command


        return applicantRepository.load(applicantId)
                .zipWith(leaveRequestFactory.build(applicantId, leaveRequestType, from, to, timeFrom, timeTo, alternateId))
                .map { it.t1.submit(it.t2) }
                .doOnError { throw RuntimeException("failed to load leave entitlements") }
                .map {
                    it.fold(
                        { error -> OperationResult.error(listOf(error.message!!)) },
                        { _ ->
                            leaveRequestRepository.save(it.getOrNull()!!)
                            OperationResult.success()
                        }
                    )
                }
    }

}