package com.manka.bff.controllers

import com.manka.bff.domain.DeckDto
import com.manka.bff.infra.MessageStructure
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.LocalDateTime
import java.util.*

@RestController
@Tag(name = "Deck API")
@RequestMapping(value = ["/api/web-client/deck"])
class DeckController(
    val testService: TestService
) {

    @PostMapping("/test")
    fun test(): ResponseEntity<Any> {
        val message = MessageStructure(
            messageId = UUID.randomUUID().toString(),
            content = "Stringdfdf",
            correlationId = UUID.randomUUID().toString(),
            sendTo = "sendTo",
            replyTo = "replay",
            timeStamp = LocalDateTime.now()
        )
        val answer: Any = testService.kafkaRequestReply(message)
        println(answer.toString())
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    @Operation(description = "Get deck by id", security = [SecurityRequirement(name = "bearerAuth")])
    fun getDeckById(@PathVariable id: String): Optional<DeckDto> {
        return Optional.empty()
    }

    @GetMapping("/")
    @Operation(description = "get all decks", security = [SecurityRequirement(name = "bearerAuth")])
    fun getAllDecks(principal: Principal): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    @Operation(description = "Crate deck", security = [SecurityRequirement(name = "bearerAuth")])
    fun updateDeck(@PathVariable id: String): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @PostMapping
    @Operation(description = "Crate deck", security = [SecurityRequirement(name = "bearerAuth")])
    fun createDeck(@RequestParam name: String, principal: Principal): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Crate deck", security = [SecurityRequirement(name = "bearerAuth")])
    fun deleteDeck(@PathVariable id: String): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @PutMapping("/add-word-toDeck")
    @Operation(description = "add word to deck", security = [SecurityRequirement(name = "bearerAuth")])
    fun addWordToDeck(
        @RequestParam idDeck: String,
        @RequestParam wordId: String,
        principal: Principal
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/deleteWordFromDeck")
    @Operation(description = "delete from deck")
    fun deleteWordFromDeck(@RequestParam idDeck: String, idWordAndStatId: String): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @PostMapping("/addUserToDeck")
    @Operation(description = "addUserToDeck")
    fun addUserToDeck(@RequestParam login: String, @RequestParam deckId: String): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @PostMapping("/deleteUserFromDeck")
    @Operation(description = "deleteUserFromDeck")
    fun deleteUserFromDeck(@RequestParam login: String, @RequestParam deckId: String): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }

    @PutMapping("/copyDeck")
    @Operation(description = "copyDeck", security = [SecurityRequirement(name = "bearerAuth")])
    fun copyDeck(principal: Principal, idDeck: String): ResponseEntity<Any> {
        return ResponseEntity.ok().build()
    }


}