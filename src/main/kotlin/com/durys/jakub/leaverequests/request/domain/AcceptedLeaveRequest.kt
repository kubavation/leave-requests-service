package com.durys.jakub.leaverequests.request.domain

internal class AcceptedLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {

    override fun information(): LeaveRequestInformation = information

}