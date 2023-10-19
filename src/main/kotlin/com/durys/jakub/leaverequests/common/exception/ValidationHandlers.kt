package com.durys.jakub.leaverequests.common.exception

internal class ValidationHandlers {

    companion object {
        fun throwingValidationExceptionHandler() = ThrowingValidationExceptionHandler()
        fun aggregatingValidationExceptionHandler() = AggregatingValidationExceptionHandler()
    }
}