package ch.saldo.domain.transaction

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.UUID

interface TransactionRepository : JpaRepository<Transaction, UUID> {
    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate): List<Transaction>
    fun findByAccountAccountId(accountId: UUID): List<Transaction>
    fun findByAccountAccountIdAndDateBetween(accountId: UUID, startDate: LocalDate, endDate: LocalDate): List<Transaction>
}