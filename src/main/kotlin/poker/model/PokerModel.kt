package poker.model

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import poker.model.domain.ApiResponse
import poker.model.simulation.calculateOutcomeProbabilities
import poker.model.simulation.translateToCard

@SpringBootApplication
class PokerModelApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<PokerModelApplication>(*args)
}

@RestController
class Controller {

    @GetMapping("/outcomes")
    @ResponseBody
    fun getHandOutcomes(
        @RequestParam(value = "totalSims", defaultValue = "10000") totalSims: Long,
        @RequestParam(value = "totalPlayers", defaultValue = "2") totalPlayers: Int,
        @RequestParam(value = "pot", defaultValue = "0") pot: Long,
        @RequestParam(value = "betToLose", defaultValue = "0") betToLose: Long,
        @RequestParam(value = "myCards") myCards: List<Int>,
        @RequestParam(value = "tableCards", defaultValue = "") tableCards: List<Int>
    ): ResponseEntity<ApiResponse> {
        val myCardsTranslated = myCards.map { it.translateToCard() }
        val tableCardsTranslated = tableCards.map { it.translateToCard() }

        println(myCardsTranslated)
        println(tableCardsTranslated)
        val response = calculateOutcomeProbabilities(
            totalSims,
            totalPlayers,
            pot,
            betToLose,
            myCardsTranslated,
            tableCardsTranslated
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
