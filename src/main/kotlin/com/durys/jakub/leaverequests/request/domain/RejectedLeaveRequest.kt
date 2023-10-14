package com.durys.jakub.leaverequests.request.domain

internal class RejectedLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}