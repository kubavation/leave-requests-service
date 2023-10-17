package com.durys.jakub.leaverequests.acceptant.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

internal interface AcceptantRepository {
    fun load(id: AcceptantId): Mono<Acceptant>
    fun load(request: LeaveRequest): Mono<Acceptant>
    fun loadAll(applicantId: ApplicantId, at: LocalDate): Flux<Acceptant>
}