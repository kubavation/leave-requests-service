package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.SendLeaveRequestToAcceptationCommand
import com.durys.jakub.leaverequests.request.domain.flow.FindSentForAcceptationLeaveRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.util.*

@Component
internal class SendLeaveRequestToAcceptationCommandHandler(
        private val applicantRepository: ApplicantRepository,
        private val acceptantRepository: AcceptantRepository,
        private val findSentForAcceptationLeaveRequest: FindSentForAcceptationLeaveRequest,
        private val leaveRequestRepository: LeaveRequestRepository): CommandHandler<SendLeaveRequestToAcceptationCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: SendLeaveRequestToAcceptationCommand): Mono<OperationResult> {

        return findSentForAcceptationLeaveRequest.find(command.requestId)
                .flatMap {
                    applicantRepository.load(it.information().applicantId)
                            .map { applicant -> applicant.sendForAcceptation(it, AcceptantId(UUID.randomUUID())) } }
                .map {
                    leaveRequestRepository.save(it)
                    OperationResult.success()
                }

    }
}