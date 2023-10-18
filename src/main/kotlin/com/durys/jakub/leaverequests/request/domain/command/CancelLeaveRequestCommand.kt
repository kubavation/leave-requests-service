package com.durys.jakub.leaverequests.request.domain.command

import com.durys.jakub.leaverequests.cqrs.command.Command
import com.durys.jakub.leaverequests.request.domain.vo.LeaveRequestId

internal class CancelLeaveRequestCommand(val leaveRequestId: LeaveRequestId): Command