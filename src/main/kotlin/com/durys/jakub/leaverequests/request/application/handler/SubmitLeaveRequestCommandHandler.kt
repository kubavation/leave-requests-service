package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.request.domain.LeaveRequestFactory
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.SubmitLeaveRequestCommand
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class SubmitLeaveRequestCommandHandler(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val applicantRepository: ApplicantRepository,
        private val leaveRequestFactory: LeaveRequestFactory): CommandHandler<SubmitLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: SubmitLeaveRequestCommand): Mono<OperationResult> {

        return applicantRepository.load(command.applicantId)
                .zipWith(
                        leaveRequestFactory
                                .build(command.applicantId, command.leaveRequestType,  command.from,
                                       command.to, command.timeFrom, command.timeTo, command.alternateId))
                .map { it.t1.submit(it.t2) }
                .doOnError { throw RuntimeException("failed to load leave entitlements") }
                .map {
                    it.fold(
                            { error -> OperationResult.error(listOf(error.message!!)) },
                            { _ ->
                                leaveRequestRepository.save(it.getOrNull()!!)
                                OperationResult.success()
                            }
                    )
                }
    }

}