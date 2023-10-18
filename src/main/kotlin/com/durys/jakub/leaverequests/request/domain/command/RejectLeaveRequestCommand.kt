package com.durys.jakub.leaverequests.request.domain.command

import com.durys.jakub.leaverequests.cqrs.command.Command
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId
import com.durys.jakub.leaverequests.request.domain.vo.RejectionReason

internal class RejectLeaveRequestCommand(val requestId: LeaveRequestId, val rejectionReason: RejectionReason): Command