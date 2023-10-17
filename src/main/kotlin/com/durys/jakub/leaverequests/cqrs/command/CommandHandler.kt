package com.durys.jakub.leaverequests.cqrs.command

interface CommandHandler<T: Command, R> {
    fun handle(command: T): R
}