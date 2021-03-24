package poker.model.simulation

import poker.model.model.EventSummary
import poker.model.model.EventDetails
import poker.model.model.HandProbabilities
import poker.model.model.HandType
import poker.model.model.MatchResult
import poker.model.transformer.TableDetails
import kotlin.math.roundToLong

fun getMatchSummary(
    sim: List<MatchResult>,
    tableDetails: TableDetails
): EventSummary {
    val winProbability = sim.map { it.isHandWinning }.count { it }.div(sim.size.toDouble())
    return EventSummary(
        eventDetails = EventDetails(
            totalSimulations = sim.size.toLong(),
            winProb = winProbability,
            expectedValue = calculateExpectedValue(winProbability, tableDetails.pot, tableDetails.betToLose),
            optimumBet = calculateOptimumBet(winProbability, tableDetails.pot)
        ),
        myHandTypeProbabilities = HandProbabilities(
            highCard = sim.getProbOfMyHandType(HandType.HIGH_CARD),
            pair = sim.getProbOfMyHandType(HandType.PAIR),
            twoPair = sim.getProbOfMyHandType(HandType.TWO_PAIR),
            triple = sim.getProbOfMyHandType(HandType.TRIPLE),
            straight = sim.getProbOfMyHandType(HandType.STRAIGHT),
            flush = sim.getProbOfMyHandType(HandType.FLUSH),
            fullHouse = sim.getProbOfMyHandType(HandType.FULL_HOUSE),
            quadruple = sim.getProbOfMyHandType(HandType.QUADRUPLE),
            straightFlush = sim.getProbOfMyHandType(HandType.STRAIGHT_FLUSH)
        ),
        bestHandTypeProbabilities = HandProbabilities(
            highCard = sim.getProbOfBestHandType(HandType.HIGH_CARD),
            pair = sim.getProbOfBestHandType(HandType.PAIR),
            twoPair = sim.getProbOfBestHandType(HandType.TWO_PAIR),
            triple = sim.getProbOfBestHandType(HandType.TRIPLE),
            straight = sim.getProbOfBestHandType(HandType.STRAIGHT),
            flush = sim.getProbOfBestHandType(HandType.FLUSH),
            fullHouse = sim.getProbOfBestHandType(HandType.FULL_HOUSE),
            quadruple = sim.getProbOfBestHandType(HandType.QUADRUPLE),
            straightFlush = sim.getProbOfBestHandType(HandType.STRAIGHT_FLUSH)
        )
    )
}

private fun calculateExpectedValue(winProb: Double, cashToWin: Long, cashToLose: Long): Long =
    (winProb * cashToWin - (1 - winProb) * cashToLose).roundToLong()

private fun calculateOptimumBet(winProb: Double, cashToWin: Long): Long =
    (winProb * cashToWin).div(1 - winProb).roundToLong()

private fun List<MatchResult>.getProbOfMyHandType(handType: HandType): Double =
    this.map { it.playerHand }.count { it == handType }.div(this.size.toDouble())

private fun List<MatchResult>.getProbOfBestHandType(handType: HandType): Double =
    this.map { it.bestHandType }.count { it == handType }.div(this.size.toDouble())
