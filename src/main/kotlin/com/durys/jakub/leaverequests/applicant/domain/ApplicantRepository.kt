package com.durys.jakub.leaverequests.applicant.domain

import reactor.core.publisher.Mono

internal interface ApplicantRepository {
    fun load(id: ApplicantId): Mono<Applicant>
}