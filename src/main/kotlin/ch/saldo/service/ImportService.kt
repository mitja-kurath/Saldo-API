package ch.saldo.service

import ch.saldo.domain.account.AccountRepository
import ch.saldo.domain.csv.SGKBCsvParser
import ch.saldo.domain.transaction.Transaction
import ch.saldo.domain.transaction.TransactionRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class ImportService(
    private val csvParser: SGKBCsvParser,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {
    fun importTransactions(file: MultipartFile, accountId: UUID): Boolean {
        val account = accountRepository.findById(accountId).orElse(null) ?: return false
        val transactions = csvParser.parse(file).map {
            Transaction(amount = it.amount, date = it.date, description = it.description, account = account)
        }
        transactionRepository.saveAll(transactions)
        return true
    }
}
