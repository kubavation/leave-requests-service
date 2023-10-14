package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal class AcceptedLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {

    override fun information(): LeaveRequestInformation = information

}