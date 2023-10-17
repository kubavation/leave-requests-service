package com.durys.jakub.leaverequests.acceptant.infastructure

import com.durys.jakub.leaverequests.acceptant.domain.Acceptant
import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

internal class RestAcceptantRepository(private val client: WebClient): AcceptantRepository {

    override fun load(id: AcceptantId): Mono<Acceptant> {
        return Mono.empty()
    }

    override fun load(request: LeaveRequest): Mono<Acceptant> {
        return client.get()
                .uri { builder -> builder
                        .path("/employees/{employeeId}")
                        .queryParam("at", LocalDate.now())
                        .queryParam("level", 1) //todo based on request
                        .build(request.information().applicantId.value) }
                .retrieve()
                .bodyToMono(Acceptant::class.java)
                .onErrorResume { Mono.empty() }
    }

    override fun loadAll(applicantId: ApplicantId, at: LocalDate): Flux<Acceptant> {
        return client.get()
                .uri {  builder -> builder
                        .path("/employees/{employeeId}")
                        .queryParam("at", LocalDate.now())
                        .build(applicantId.value) }
                .retrieve()
                .bodyToFlux(Acceptant::class.java)
                .onErrorResume { Flux.empty() }
    }
}
