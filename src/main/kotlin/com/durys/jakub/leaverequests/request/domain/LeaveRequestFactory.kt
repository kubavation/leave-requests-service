package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.sharedkernel.identityprovider.IdentityProvider
import reactor.core.publisher.Mono
import java.time.LocalDate

internal class LeaveRequestFactory {

    companion object {

        fun create(identityProvider: IdentityProvider, applicantId: ApplicantId,
                   from: LocalDate, to: LocalDate, leaveRequestType: LeaveRequestType, alternateId: AlternateId?): Mono<LeaveRequest> {

            return Mono.just(
                    LeaveRequest(LeaveRequestId(identityProvider.next()), applicantId, Settlement.daily(from, to), leaveRequestType, alternateId))
        }
    }

}