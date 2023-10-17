package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.Acceptation
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal class AcceptedLeaveRequest(private val information: LeaveRequestInformation, private val acceptation: Acceptation): LeaveRequest {

    override fun information(): LeaveRequestInformation = information

    override fun acceptation() = acceptation

}