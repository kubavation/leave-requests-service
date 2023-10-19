package com.durys.jakub.leaverequests.common.exception

internal class ThrowingValidationExceptionHandler: ValidationExceptionHandler {

    override fun handle(exception: Exception) = throw exception
}