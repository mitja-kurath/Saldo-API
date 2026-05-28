package ch.saldo.graphql

import ch.saldo.domain.transaction.Transaction
import ch.saldo.domain.transaction.TransactionRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.LocalDate
import java.util.UUID

@Controller
class TransactionResolver(
    val transactionRepository: TransactionRepository
) {

    @QueryMapping
    fun transactions(
        @Argument startDate: LocalDate? = null,
        @Argument endDate: LocalDate? = null,
        @Argument sort: Sort? = null,
        @Argument sortBy: SortBy? = null
    ): List<Transaction> {

        val transactions = if (startDate != null && endDate != null) {
            transactionRepository.findByDateBetween(startDate, endDate)
        } else {
            transactionRepository.findAll()
        }

        if (sort == null || sortBy == null) return transactions

        return when (sort) {
            Sort.ASC -> when (sortBy) {
                SortBy.DATE -> transactions.sortedBy { it.date }
                SortBy.AMOUNT -> transactions.sortedBy { it.amount }
            }

            Sort.DESC -> when (sortBy) {
                SortBy.DATE -> transactions.sortedByDescending { it.date }
                SortBy.AMOUNT -> transactions.sortedByDescending { it.amount }
            }
        }
    }

    @QueryMapping
    fun transaction(@Argument id: UUID): Transaction? {
        return transactionRepository.findById(id).orElse(null)
    }

    @MutationMapping
    fun updateTransactionDescription(@Argument id: UUID, @Argument description: String): Transaction? {
        val transaction = transactionRepository.findById(id).orElse(null)

        if (transaction != null) {
            transaction.description = description
            return transactionRepository.save(transaction)
        } else {
            throw NoSuchElementException("Transaction not found")
        }
    }
}