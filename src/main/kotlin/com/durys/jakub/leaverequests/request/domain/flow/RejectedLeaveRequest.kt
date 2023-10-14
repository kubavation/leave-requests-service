package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation
import com.durys.jakub.leaverequests.request.domain.vo.RejectionReason

internal class RejectedLeaveRequest(private val information: LeaveRequestInformation,
                                    private val rejectionReason: RejectionReason): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}