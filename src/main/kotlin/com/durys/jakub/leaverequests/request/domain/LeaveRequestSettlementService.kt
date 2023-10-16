package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate

@Component
internal class LeaveRequestSettlementService {

    fun hoursDefinitionRequired(applicantId: ApplicantId, requestType: LeaveRequestType, at: LocalDate): Mono<Boolean> {
        return Mono.just(false); //todo
    }
}