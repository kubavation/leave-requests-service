package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.SendLeaveRequestToAcceptationCommand
import com.durys.jakub.leaverequests.request.domain.flow.FindSubmittedLeaveRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class SendLeaveRequestToAcceptationCommandHandler(
        private val applicantRepository: ApplicantRepository,
        private val acceptantRepository: AcceptantRepository,
        private val findSubmittedLeaveRequest: FindSubmittedLeaveRequest,
        private val leaveRequestRepository: LeaveRequestRepository): CommandHandler<SendLeaveRequestToAcceptationCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: SendLeaveRequestToAcceptationCommand): Mono<OperationResult> {

        return findSubmittedLeaveRequest.find(command.requestId)
                .flatMap { request ->
                    applicantRepository.load(request.information().applicantId)
                            .zipWith(acceptantRepository.load(request))
                            .map { it.t1.sendForAcceptation(request, it.t2) } }
                .map {
                    leaveRequestRepository.save(it)
                    OperationResult.success()
                }
                .doOnError { OperationResult.error(emptyList()) }

    }
}