package com.durys.jakub.leaverequests.request.application

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlementsId
import com.durys.jakub.leaverequests.entitlements.domain.LeaveEntitlementsRepository
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.Settlement
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.sharedkernel.identityprovider.IdentityProvider
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate

@Component
internal class LeaveRequestApplicationService(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val leaveEntitlementsRepository: LeaveEntitlementsRepository,
        private val identityProvider: IdentityProvider) {

    internal fun submit(applicantId: ApplicantId, leaveRequestType: LeaveRequestType,
                        from: LocalDate, to: LocalDate, alternateId: AlternateId?): Mono<OperationResult> {

        val leaveRequest = LeaveRequest(
                LeaveRequestId(identityProvider.next()),
                applicantId,
                Settlement.daily(from, to),
                leaveRequestType,
                alternateId)

        return leaveEntitlementsRepository.load(LeaveEntitlementsId(applicantId, leaveRequestType, LocalDate.now()))
                .map { it.valid(leaveRequest) }
                .doOnError { throw RuntimeException("failed to load leave entitlements") }
                .map {
                    it.fold(
                        { error -> OperationResult.error(listOf(error.message!!)) },
                        { _ ->
                            leaveRequestRepository.save(leaveRequest)
                            OperationResult.success()
                        }
                    )
                }
    }

}