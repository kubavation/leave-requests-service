package com.durys.jakub.leaverequests.request.domain.vo

import com.durys.jakub.leaverequests.acceptant.domain.AcceptantId
import com.durys.jakub.leaverequests.sharedkernel.acceptation.AcceptationLevel

internal class Acceptation(val acceptantId: AcceptantId?, val acceptationLevel: AcceptationLevel) {

    companion object {
        fun default() = Acceptation(null, AcceptationLevel(0))
    }

    fun increment(acceptantId: AcceptantId) = Acceptation(acceptantId, AcceptationLevel(acceptationLevel.value() + 1))

}