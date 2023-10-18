package com.durys.jakub.leaverequests.cqrs.event

internal interface Events {
    fun <T> emit(event: T)
}