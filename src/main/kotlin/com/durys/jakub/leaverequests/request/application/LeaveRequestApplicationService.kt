package com.durys.jakub.leaverequests.request.application

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.Settlement
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.sharedkernel.identityprovider.IdentityProvider
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
internal class LeaveRequestApplicationService(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val identityProvider: IdentityProvider) {

    internal fun submit(applicantId: ApplicantId, leaveRequestType: LeaveRequestType,
                        from: LocalDate, to: LocalDate, alternateId: AlternateId?) {

        val leaveRequest = LeaveRequest(
                LeaveRequestId(identityProvider.next()),
                applicantId,
                Settlement.daily(from, to),
                leaveRequestType,
                alternateId)

        leaveRequestRepository.save(leaveRequest)
    }

}