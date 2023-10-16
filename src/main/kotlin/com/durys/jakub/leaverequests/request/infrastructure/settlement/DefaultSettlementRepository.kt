package com.durys.jakub.leaverequests.request.infrastructure.settlement

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementRepository
import com.durys.jakub.leaverequests.request.domain.settlement.SettlementType
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.LocalDate

@Component
internal class DefaultSettlementRepository: SettlementRepository {

    override fun resolve(applicantId: ApplicantId, requestType: LeaveRequestType, at: LocalDate): Mono<SettlementType> {
        return Mono.just(SettlementType.DAILY) //todo
    }
}