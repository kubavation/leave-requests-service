package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.CancelLeaveRequestCommand
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class CancelLeaveRequestCommandHandler(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val applicantRepository: ApplicantRepository): CommandHandler<CancelLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: CancelLeaveRequestCommand): Mono<OperationResult> {
        return leaveRequestRepository.load(command.leaveRequestId)
                .flatMap {
                    request -> applicantRepository.load(request.applicant())
                        .map { applicant -> applicant.cancel(request) } }
                .map { OperationResult.success() }
    }
}