package poker.model.simulation

import poker.model.model.MatchSummary
import poker.model.model.EventDetails
import poker.model.model.HandProbabilities
import poker.model.model.HandType
import poker.model.model.MatchRecord
import poker.model.model.MatchResults
import poker.model.model.TableDetails
import kotlin.math.roundToLong

class MatchSummaryConverter {
    fun getMatchSummary(matchResults: MatchResults): MatchSummary {
        val handTypesGrouped = groupByHandType(matchResults.matchRecords)
        val playerWinningHands = handTypesGrouped.mapToWinningHands()
        val opponentWinningHands = handTypesGrouped.mapToLosingHands()
        val totalMatches = matchResults.matchRecords.size

        return MatchSummary(
            eventDetails = getEventDetails(matchResults.matchRecords, matchResults.tableDetails),
            myHandTypeProbabilities = getHandProbabilities(playerWinningHands, totalMatches),
            bestHandTypeProbabilities = getHandProbabilities(opponentWinningHands, totalMatches)
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

    private fun groupByHandType(matchResults: List<MatchRecord>): Map<HandType, List<MatchRecord>> =
        matchResults.groupBy { it.playerHand }

    private fun Map<HandType, List<MatchRecord>>.mapToWinningHands(): Map<HandType, Int> =
        this.mapValues { it.value.filter { matchResult ->  matchResult.isHandWinning }.size }

    private fun Map<HandType, List<MatchRecord>>.mapToLosingHands(): Map<HandType, Int> =
        this.mapValues { it.value.filter { matchResult -> !matchResult.isHandWinning }.size }


    private fun getHandProbabilities(winningHands: Map<HandType, Int>, totalMatches: Int) =
        HandProbabilities(
            highCard = probabilityOfHandType(winningHands, HandType.HIGH_CARD, totalMatches),
            pair = probabilityOfHandType(winningHands, HandType.PAIR, totalMatches),
            twoPair = probabilityOfHandType(winningHands, HandType.TWO_PAIR, totalMatches),
            triple = probabilityOfHandType(winningHands, HandType.TRIPLE, totalMatches),
            straight = probabilityOfHandType(winningHands, HandType.STRAIGHT, totalMatches),
            flush = probabilityOfHandType(winningHands, HandType.FLUSH, totalMatches),
            fullHouse = probabilityOfHandType(winningHands, HandType.FULL_HOUSE, totalMatches),
            quadruple = probabilityOfHandType(winningHands, HandType.QUADRUPLE, totalMatches),
            straightFlush = probabilityOfHandType(winningHands, HandType.STRAIGHT_FLUSH, totalMatches)
        )

    private fun probabilityOfHandType(winningHands: Map<HandType, Int>, handType: HandType, totalMatches: Int): Double {
        val totalSims = totalMatches.toDouble()
        val totalWins = winningHands[handType]
        return totalWins?.div(totalSims) ?: 0.0
    }
}
