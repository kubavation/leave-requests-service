package com.durys.jakub.leaverequests.acceptant

import com.durys.jakub.leaverequests.request.domain.LeaveRequest

internal interface AcceptantRepository {
    fun load(id: AcceptantId): Acceptant
    fun load(request: LeaveRequest): Acceptant
}