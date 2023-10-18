package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.command.RejectLeaveRequestCommand
import com.durys.jakub.leaverequests.request.domain.flow.FindSentForAcceptationLeaveRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class RejectLeaveRequestCommandHandler(
        val findSentForAcceptationLeaveRequest: FindSentForAcceptationLeaveRequest,
        val acceptantRepository: AcceptantRepository): CommandHandler<RejectLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: RejectLeaveRequestCommand): Mono<OperationResult> {

        return findSentForAcceptationLeaveRequest.findSentForAcceptation(command.requestId)
                .flatMap {
                    request -> acceptantRepository.load(request.acceptant()!!)
                        .map { acceptant -> acceptant.reject(request, command.rejectionReason) } }
                .map { OperationResult.success() }
                .doOnError { OperationResult.error(listOf(it.message!!)) }
    }
}