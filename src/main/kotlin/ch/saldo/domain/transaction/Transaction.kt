package ch.saldo.domain.transaction

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "transactions")
class Transaction(
    val amount: Int,
    val date: String,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null
    var description: String? = null

}