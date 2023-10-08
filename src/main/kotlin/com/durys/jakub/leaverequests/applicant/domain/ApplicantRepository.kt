package com.durys.jakub.leaverequests.applicant.domain

internal interface ApplicantRepository {
    fun load(id: ApplicantId): Applicant;
}