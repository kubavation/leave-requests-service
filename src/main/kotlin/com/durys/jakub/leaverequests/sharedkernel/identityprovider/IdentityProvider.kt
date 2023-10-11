package com.durys.jakub.leaverequests.sharedkernel.identityprovider

import java.util.*

interface IdentityProvider {
    fun next(): UUID
}