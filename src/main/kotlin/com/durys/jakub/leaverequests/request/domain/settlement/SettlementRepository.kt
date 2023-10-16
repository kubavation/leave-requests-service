package com.durys.jakub.leaverequests.request.domain.settlement

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import reactor.core.publisher.Mono
import java.time.LocalDate

internal interface SettlementRepository {
    fun resolve(applicantId: ApplicantId, requestType: LeaveRequestType, at: LocalDate = LocalDate.now()): Mono<SettlementType>
}