package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.command.RejectLeaveRequestCommand
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class RejectLeaveRequestCommandHandler: CommandHandler<RejectLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: RejectLeaveRequestCommand): Mono<OperationResult> {
        TODO("Not yet implemented")
    }
}