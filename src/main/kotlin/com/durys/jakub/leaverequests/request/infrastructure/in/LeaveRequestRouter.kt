package com.durys.jakub.leaverequests.request.infrastructure.`in`

import com.durys.jakub.leaverequests.applicant.domain.ApplicantId
import com.durys.jakub.leaverequests.common.OperationResult
import com.durys.jakub.leaverequests.request.application.handler.SubmitLeaveRequestCommandHandler
import com.durys.jakub.leaverequests.request.domain.command.SubmitLeaveRequestCommand
import com.durys.jakub.leaverequests.request.domain.vo.AlternateId
import com.durys.jakub.leaverequests.request.infrastructure.`in`.model.CreateLeaveRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import java.util.*

@Configuration
internal class LeaveRequestRouter {

    @Bean
    fun router(submitLeaveRequestCommandHandler: SubmitLeaveRequestCommandHandler)
            = org.springframework.web.reactive.function.server.router {
        accept(MediaType.APPLICATION_JSON).nest {
            "leave-requests".nest {
                POST("") { request ->
                    request.bodyToMono(CreateLeaveRequest::class.java)
                            .flatMap {
                                submitLeaveRequestCommandHandler.handle(
                                        SubmitLeaveRequestCommand(
                                                ApplicantId(UUID.fromString(it.applicantId)),
                                                it.type, it.from, it.to, it.timeFrom, it.timeTo, AlternateId(UUID.fromString(it.alternateId))))
                            }
                            .flatMap { ServerResponse.ok().body(it, OperationResult::class.java) }
                }

            }
        }
    }

}