package com.durys.jakub.leaverequests.request.domain

internal class RejectedLeaveRequest(private val information: LeaveRequestInformation,
                                    private val rejectionReason: RejectionReason): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}