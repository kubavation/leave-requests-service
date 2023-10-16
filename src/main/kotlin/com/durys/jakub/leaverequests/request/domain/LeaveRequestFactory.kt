package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.request.domain.flow.WorkingLeaveRequest
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementRepository
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.sharedkernel.identityprovider.IdentityProvider
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalTime

@Component
internal class LeaveRequestFactory(private val identityProvider: IdentityProvider,
                                   private val periodFactory: PeriodFactory,
                                   private val settlementRepository: SettlementRepository) {

        internal fun build(applicantId: ApplicantId, leaveRequestType: LeaveRequestType,
                           from: LocalDate, to: LocalDate, timeFrom: LocalTime?, timeTo: LocalTime?,
                           alternateId: AlternateId?): Mono<WorkingLeaveRequest> {

                return periodFactory.period(applicantId, leaveRequestType, from, to, timeFrom, timeTo)
                        .zipWith(settlementRepository.resolve(applicantId, leaveRequestType, to))
                        .map { WorkingLeaveRequest(
                                LeaveRequestId(identityProvider.next()),
                                leaveRequestType, it.t1, it.t2, alternateId) }
        }

}