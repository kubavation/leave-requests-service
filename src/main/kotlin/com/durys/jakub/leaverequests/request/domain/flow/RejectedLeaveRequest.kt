package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.Acceptation
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation
import com.durys.jakub.leaverequests.request.domain.vo.RejectionReason

internal class RejectedLeaveRequest(private val information: LeaveRequestInformation,
                                    private val rejectionReason: RejectionReason {


    override fun information(): LeaveRequestInformation = information

    override fun acceptation() = acceptation

}