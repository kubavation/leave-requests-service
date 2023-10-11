package com.durys.jakub.leaverequests.sharedkernel.identityprovider

import org.springframework.stereotype.Component
import java.util.*

@Component
class DefaultIdentityProvider: IdentityProvider {

    override fun next(): UUID = UUID.randomUUID()
}