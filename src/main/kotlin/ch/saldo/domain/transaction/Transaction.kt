package ch.saldo.domain.transaction

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

import ch.saldo.domain.account.Account

@Entity
@Table(name = "transactions")
class Transaction(
    val amount: Int,
    val date: LocalDate,
    var description: String? = null,
    account: Account? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "account_id")
    var account: Account? = account
}
