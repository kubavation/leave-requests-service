package com.durys.jakub.leaverequests.request.events

import com.durys.jakub.leaverequests.cqrs.event.Event
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

internal class LeaveRequestAccepted(val personId: String, val from: LocalDate, val to: LocalDate,
                           val timeFrom: LocalTime?, val timeTo: LocalTime): Event {


    private val id: UUID = UUID.randomUUID()
    private val at: LocalDateTime = LocalDateTime.now()

    override fun id() = id

    override fun at() = at

}