package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.AcceptLeaveRequestCommand
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class AcceptLeaveRequestCommandHandler(val leaveRequestRepository: LeaveRequestRepository): CommandHandler<AcceptLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: AcceptLeaveRequestCommand): Mono<OperationResult> {
        TODO("Not yet implemented")
    }
}