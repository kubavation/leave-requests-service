package com.durys.jakub.leaverequests.request.domain

import com.durys.jakub.leaverequests.request.domain.vo.Acceptation
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestInformation

internal interface LeaveRequest {

    fun information(): LeaveRequestInformation

    fun settlement() = information().settlement

    fun id() = information().id

    fun type() = information().type

    fun acceptation(): Acceptation

    fun level() = acceptation().acceptationLevel
    fun acceptant() = acceptation().acceptantId
}