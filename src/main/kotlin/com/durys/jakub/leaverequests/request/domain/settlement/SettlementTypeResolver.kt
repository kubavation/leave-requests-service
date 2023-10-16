package com.durys.jakub.leaverequests.request.domain.settlement

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.SettlementType
import org.springframework.stereotype.Service

@Service
internal class SettlementTypeResolver {

    fun resolve(applicantId: ApplicantId, requestType: LeaveRequestType): SettlementType {
        //TODO explore domain
        return SettlementType.DAILY;
    }
}