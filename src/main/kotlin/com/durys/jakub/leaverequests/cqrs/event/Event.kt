package com.durys.jakub.leaverequests.cqrs.event

import java.time.LocalDateTime
import java.util.*

internal interface Event {
    fun id(): UUID
    fun at(): LocalDateTime
}