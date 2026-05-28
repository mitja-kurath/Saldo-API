package ch.saldo.domain.account

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "accounts")
class Account(
    val name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val accountId: UUID? = null

}