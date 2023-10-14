package com.durys.jakub.leaverequests.request.domain

internal class CancelledLeaveRequest(private val information: LeaveRequestInformation): LeaveRequest {


    override fun information(): LeaveRequestInformation = information

}