package com.bsfdv.backend.domain.service.account

import com.bsfdv.backend.domain.model.account.Account
import com.bsfdv.backend.domain.model.account.AccountHolder
import com.bsfdv.backend.domain.model.common.Money
import com.bsfdv.backend.domain.service.core.EventReader
import com.bsfdv.backend.suites.UNIT_TEST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.math.BigDecimal

@Tag(UNIT_TEST)
internal class AccountReadRepositoryTest {

    private lateinit var accounts: Accounts
    private lateinit var eventReader: EventReader
    private lateinit var account1: Account
    private lateinit var account2: Account
    private lateinit var account3: Account

    @BeforeEach
    fun setUp() {
        eventReader = mock(EventReader::class.java)
        accounts = AccountReadRepository(eventReader)
        account1 = Account.openAccount(AccountHolder("Joh", "Doe"), Money(BigDecimal(2000)))
        account2 = Account.openAccount(AccountHolder("Jessica", "Doe"), Money(BigDecimal(0)))
        account3 = Account.openAccount(AccountHolder("Amelie", "Scarlit"), Money(BigDecimal(100)))
        given(eventReader.byType(Account::class)).willReturn(
            listOf(
                account1.history,
                account2.history,
                account3.history
            )
        )
    }

    @Test
    fun can_retrieve_all_accounts() {
        // given

        // when
        val result = accounts.all()

        // then
        assertThat(result).containsExactlyInAnyOrder(account1, account2, account3)
    }
}