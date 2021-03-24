package poker.model.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import poker.model.domain.Response
import poker.model.simulation.SimulationHandler
import poker.model.transformer.CardTransformer

@RestController
class Controller(
    val cardTransformer: CardTransformer = CardTransformer(),
    val simulationHandler: SimulationHandler = SimulationHandler()
) {

    @GetMapping("/outcomes")
    @ResponseBody
    fun getHandOutcomes(
        @RequestParam(value = "totalPlayers", defaultValue = "2") totalPlayers: Int,
        @RequestParam(value = "pot", defaultValue = "0") pot: Long,
        @RequestParam(value = "betToLose", defaultValue = "0") betToLose: Long,
        @RequestParam(value = "myCards") myCards: List<Int>,
        @RequestParam(value = "tableCards", defaultValue = "") tableCards: List<Int>
    ): ResponseEntity<Response?> {
        val tableDetails = cardTransformer.toTableDetails(totalPlayers, pot, betToLose, myCards, tableCards)

        val matchResults = simulationHandler.getMatchResults(tableDetails)

        val response = null
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}