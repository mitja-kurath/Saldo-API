package ch.saldo.domain.transaction

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.LocalDate

@Converter(autoApply = true)
class LocalDateConverter : AttributeConverter<LocalDate, String> {
    override fun convertToDatabaseColumn(attribute: LocalDate?): String? =
        attribute?.toString()

    override fun convertToEntityAttribute(dbData: String?): LocalDate? =
        dbData?.let { LocalDate.parse(it) }
}
