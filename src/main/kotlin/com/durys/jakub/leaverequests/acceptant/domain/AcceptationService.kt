package com.durys.jakub.leaverequests.acceptant.domain

import com.durys.jakub.leaverequests.request.domain.vo.Acceptation
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
internal class AcceptationService {

    fun acceptable(requestAcceptation: Acceptation, acceptants: Flux<Acceptant>): Mono<Boolean> {
       return acceptants//todo leave request configuration
            .any { it.level().value() > requestAcceptation.acceptationLevel.value() }
    }


}