package com.durys.jakub.leaverequests.request.domain

internal interface LeaveRequest {

    fun information(): LeaveRequestInformation

    fun settlement() = information().settlement

    fun id() = information().id

}