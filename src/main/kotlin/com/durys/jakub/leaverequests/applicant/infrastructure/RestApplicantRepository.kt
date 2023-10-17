package com.durys.jakub.leaverequests.applicant.infrastructure

import com.durys.jakub.leaverequests.applicant.domain.Applicant
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
internal class RestApplicantRepository: ApplicantRepository {

    override fun load(id: ApplicantId): Mono<Applicant> {
        return Mono.just(null!!) //todo
    }
}