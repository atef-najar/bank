package com.bsfdv.backend.domain.model.account.event

import com.bsfdv.backend.domain.model.account.AccountHolder
import com.bsfdv.backend.domain.model.account.AccountId
import com.bsfdv.backend.domain.model.common.Money
import com.bsfdv.backend.domain.model.core.Event
import com.bsfdv.backend.domain.model.core.Version
import java.time.Instant

class AccountOpened(
    id: AccountId, val holder: AccountHolder, val initialBalance: Money,
    version: Version, time: Instant
) : Event<AccountId>(id, version, time)