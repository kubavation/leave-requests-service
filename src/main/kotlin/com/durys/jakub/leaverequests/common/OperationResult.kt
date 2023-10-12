package com.durys.jakub.leaverequests.common

class OperationResult(val status: ResultStatus, errors: List<String>) {

    companion object {
        fun success() = OperationResult(ResultStatus.SUCCESS, emptyList())
        fun error(errors: List<String>) = OperationResult(ResultStatus.ERROR, errors)
    }
}