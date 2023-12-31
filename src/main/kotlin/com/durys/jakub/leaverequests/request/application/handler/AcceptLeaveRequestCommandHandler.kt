package com.durys.jakub.leaverequests.request.application.handler

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import com.durys.jakub.leaverequests.acceptant.domain.AcceptationService
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.cqrs.command.CommandHandler
import com.durys.jakub.leaverequests.cqrs.event.Events
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import com.durys.jakub.leaverequests.request.domain.LeaveRequestRepository
import com.durys.jakub.leaverequests.request.domain.command.AcceptLeaveRequestCommand
import com.durys.jakub.leaverequests.request.domain.flow.FindSentForAcceptationLeaveRequest
import com.durys.jakub.leaverequests.request.events.LeaveRequestAccepted
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import java.time.LocalDate

@Component
internal class AcceptLeaveRequestCommandHandler(
        val leaveRequestRepository: LeaveRequestRepository, val acceptantRepository: AcceptantRepository,
        val acceptationService: AcceptationService, val findSentForAcceptationLeaveRequest: FindSentForAcceptationLeaveRequest,
        val events: Events): CommandHandler<AcceptLeaveRequestCommand, Mono<OperationResult>> {


    @Transactional
    override fun handle(command: AcceptLeaveRequestCommand): Mono<OperationResult> {

        return findSentForAcceptationLeaveRequest.findSentForAcceptation(command.requestId)
                .flatMap {
                    request -> acceptantRepository.load(request.acceptant()!!)
                        .zipWith(acceptationService.acceptable(request.acceptation(), acceptantRepository.loadAll(request.applicant(), LocalDate.now())))
                    .map { it.t1.approve(request, it.t2) }
                }
                .map { result ->
                    result.fold(
                            {
                                leaveRequestRepository.save(it)
                                publishEvent(it)
                                OperationResult.success()
                            },
                            {
                                leaveRequestRepository.save(it)
                                OperationResult.success() //todo notifications
                            }
                    )
                }
    }

    private fun publishEvent(leaveRequest: LeaveRequest) {
        events.emit(
                LeaveRequestAccepted(
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