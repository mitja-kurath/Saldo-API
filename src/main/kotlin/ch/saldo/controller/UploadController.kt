package ch.saldo.controller

import ch.saldo.service.ImportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
class UploadController(private val importService: ImportService) {

    @PostMapping("/api/v1/upload")
    fun upload(@RequestParam("file") file: MultipartFile, @RequestParam accountId: UUID): ResponseEntity<String> {
        if (file.isEmpty) return ResponseEntity.badRequest().body("Please provide a file")
        if (!importService.importTransactions(file, accountId)) return ResponseEntity.notFound().build()
        return ResponseEntity.ok("File uploaded successfully")
    }
}
