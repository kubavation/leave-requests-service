package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal interface LeaveRequest {

    fun information(): LeaveRequestInformation

    fun settlement() = information().settlement

    fun id() = information().id

    fun type() = information().type

}