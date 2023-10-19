package com.durys.jakub.leaverequests.common.exception

internal class AggregatingValidationExceptionHandler: ValidationExceptionHandler {

    private val errors: MutableList<Exception> = mutableListOf()

    override fun handle(exception: Exception) {
        errors.add(exception)
    }

    fun errors(): List<String> = errors.map { it.message!! }

}