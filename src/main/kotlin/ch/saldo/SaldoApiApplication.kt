package ch.saldo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SaldoApiApplication

fun main(args: Array<String>) {
    runApplication<SaldoApiApplication>(*args)
}
