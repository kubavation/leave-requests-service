package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal class CancelledLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}