package ch.saldo.domain.csv

import ch.saldo.domain.transaction.Transaction
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class SGKBCsvParser {
    fun parse(csv: MultipartFile): List<Transaction> {
        val reader = csv.inputStream.bufferedReader(Charsets.UTF_8)
        reader.mark(1)
        if (reader.read() != 0xFEFF) reader.reset()

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        return reader.readLines().drop(1).filter { it.isNotBlank() }.map { line ->
            val columns = line.split(";")
            val date = LocalDate.parse(columns[0], formatter)
            val description = columns[2].trim().removeSurrounding("\"").trim()
            val rawBelastung = columns[3].trim().replace("'", "")
            val rawGutschrift = columns[4].trim().replace("'", "")

            val amount = if (rawBelastung.isNotEmpty()) {
                -rawBelastung.toBigDecimal().movePointRight(2).toInt()
            } else {
                rawGutschrift.toBigDecimal().movePointRight(2).toInt()
            }

            Transaction(amount = amount, date = date, description = description)
        }
    }
}
