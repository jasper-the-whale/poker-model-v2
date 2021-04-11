package poker.model.simulation

import org.springframework.stereotype.Component
import poker.model.converter.convertToHandProbabilities
import poker.model.model.MatchSummary
import poker.model.model.EventDetails
import poker.model.model.MatchRecord
import poker.model.model.MatchResults
import poker.model.model.TableDetails
import kotlin.math.roundToLong

@Component
class MatchSummaryConverter {
    fun convertToMatchSummary(matchResults: MatchResults): MatchSummary {

        return MatchSummary(
            eventDetails = getEventDetails(matchResults.matchRecords, matchResults.tableDetails),
            myHandTypeProbabilities = convertToHandProbabilities(true, matchResults),
            bestHandTypeProbabilities = convertToHandProbabilities(false, matchResults)
        )
    }

    private fun getEventDetails(matchResults: List<MatchRecord>, tableDetails: TableDetails): EventDetails {
        val winProbability = playerWinProbability(matchResults)
        return EventDetails(
            totalSimulations = matchResults.size,
            winProb = winProbability,
            expectedValue = calculateExpectedValue(winProbability, tableDetails.pot, tableDetails.betToLose),
            optimumBet = calculateOptimumBet(winProbability, tableDetails.pot)
        )
    }

    private fun playerWinProbability(matchResults: List<MatchRecord>) =
        matchResults.map { it.isHandWinning }.count { it }.div(matchResults.size.toDouble())

    private fun calculateExpectedValue(winProb: Double, cashToWin: Long, cashToLose: Long): Long =
        (winProb * cashToWin - (1 - winProb) * cashToLose).roundToLong()

    private fun calculateOptimumBet(winProb: Double, cashToWin: Long): Long =
        (winProb * cashToWin).div(1 - winProb).roundToLong()

}
