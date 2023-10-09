package com.durys.jakub.leaverequests.acceptant.infastructure

import com.durys.jakub.leaverequests.acceptant.domain.Acceptant
import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.acceptant.domain.AcceptantRepository
import com.durys.jakub.leaverequests.request.domain.LeaveRequest
import org.springframework.web.reactive.function.client.WebClient

internal class RestAcceptantRepository(private val client: WebClient): AcceptantRepository {

    override fun load(id: AcceptantId): Acceptant? {
        TODO("Not yet implemented")
    }

    override fun load(request: LeaveRequest): Acceptant? {
        TODO("Not yet implemented")
    }
}