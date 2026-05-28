package ch.saldo.graphql

import ch.saldo.domain.account.Account
import ch.saldo.domain.account.AccountRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class AccountResolver(
    private val accountRepository: AccountRepository
) {
    @MutationMapping
    fun createAccount(@Argument name: String) = accountRepository.save(
        Account(name)
    )
}