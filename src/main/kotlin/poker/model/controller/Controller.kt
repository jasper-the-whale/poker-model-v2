package poker.model.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import poker.model.model.MatchSummary
import poker.model.model.ValidTableSummary
import poker.model.simulation.MatchSummaryConverter
import poker.model.simulation.SimulationHandler
import poker.model.transformer.CardTransformer

@RestController
class Controller(
    val cardTransformer: CardTransformer = CardTransformer(),
    val simulationHandler: SimulationHandler = SimulationHandler(),
    val matchSummaryConverter: MatchSummaryConverter = MatchSummaryConverter()
) {

    @GetMapping("/outcomes")
    @ResponseBody
    fun getHandOutcomes(
        @RequestParam(value = "myCards") myCards: List<Int>,
        @RequestParam(value = "totalPlayers", defaultValue = "2") totalPlayers: Int,
        @RequestParam(value = "tableCards", defaultValue = "") tableCards: List<Int>,
        @RequestParam(value = "pot", defaultValue = "0") pot: Long,
        @RequestParam(value = "betToLose", defaultValue = "0") betToLose: Long
    ): ResponseEntity<MatchSummary?> {
        val tableSummary = cardTransformer.toTableDetails(totalPlayers, pot, betToLose, myCards, tableCards)

        return if (tableSummary is ValidTableSummary) {
            val matchResults = simulationHandler.getMatchResults(tableSummary.tableDetails)
            val matchSummary = matchSummaryConverter.convertToMatchSummary(matchResults)

            ResponseEntity.status(HttpStatus.OK).body(matchSummary)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }
}