package com.durys.jakub.leaverequests.request.domain.flow

import com.durys.jakub.leaverequests.request.domain.settlement.SettlementType
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestType
import com.durys.jakub.leaverequests.request.domain.vo.Period

internal class WorkingLeaveRequest(val id: LeaveRequestId, val type: LeaveRequestType, val period: Period,
                                   val settlementType: SettlementType, val alternateId: AlternateId? = null) {
}