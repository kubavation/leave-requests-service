package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.applicant.domain.ApplicantRepository
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.cqrs.event.Events
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.CancelLeaveRequestCommand
import com.durys.jakub.leaverequests.request.events.LeaveRequestCancelled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Component
internal class CancelLeaveRequestCommandHandler(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val events: Events,
        private val applicantRepository: ApplicantRepository): CommandHandler<CancelLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: CancelLeaveRequestCommand): Mono<OperationResult> {
        return leaveRequestRepository.load(command.leaveRequestId)
                .flatMap {
                    request -> applicantRepository.load(request.applicant())
                        .map { applicant -> applicant.cancel(request) } }
                .map {
                    publishEvent(it)
                    OperationResult.success()
                }
    }

    private fun publishEvent(leaveRequest: LeaveRequest) {
        events.emit(
                LeaveRequestCancelled(
                        leaveRequest.acceptant()!!.value,
                        leaveRequest.type(),
                        leaveRequest.information().period.from(),
                        leaveRequest.information().period.to(),
                        leaveRequest.information().period.days(),
                        leaveRequest.information().period.hours(),
                        leaveRequest.information().period.timeFrom(),
                        leaveRequest.information().period.timeTo()
                ))
    }
}