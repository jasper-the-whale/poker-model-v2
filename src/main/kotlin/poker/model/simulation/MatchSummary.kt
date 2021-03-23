package poker.model.simulation

import poker.model.domain.ApiResponse
import poker.model.domain.HandProbabilities
import poker.model.domain.HandType
import poker.model.domain.MatchResult
import kotlin.math.roundToLong

fun getMatchSummary(
    sim: List<MatchResult>,
    pot: Long,
    betToLose: Long
): ApiResponse {
    val winProbability = sim.map { it.isHandWinning }.count { it }.div(sim.size.toDouble())
    return ApiResponse(
        totalSimulations = sim.size.toLong(),
        winProb = winProbability,
        expectedValue = calculateExpectedValue(winProbability, pot, betToLose),
        optimumBet = calculateOptimumBet(winProbability, pot),
        myHandTypeProbabilities = HandProbabilities(
            highCardProb = sim.getProbOfMyHandType(HandType.HIGH_CARD),
            pairProb = sim.getProbOfMyHandType(HandType.PAIR),
            twoPairProb = sim.getProbOfMyHandType(HandType.TWO_PAIR),
            tripleProb = sim.getProbOfMyHandType(HandType.TRIPLE),
            straightProb = sim.getProbOfMyHandType(HandType.STRAIGHT),
            flushProb = sim.getProbOfMyHandType(HandType.FLUSH),
            fullHouseProb = sim.getProbOfMyHandType(HandType.FULL_HOUSE),
            quadrupleProb = sim.getProbOfMyHandType(HandType.QUADRUPLE),
            straightFlushProb = sim.getProbOfMyHandType(HandType.STRAIGHT_FLUSH)
        ),
        bestHandTypeProbabilities = HandProbabilities(
            highCardProb = sim.getProbOfMyHandType(HandType.HIGH_CARD),
            pairProb = sim.getProbOfBestHandType(HandType.PAIR),
            twoPairProb = sim.getProbOfBestHandType(HandType.TWO_PAIR),
            tripleProb = sim.getProbOfBestHandType(HandType.TRIPLE),
            straightProb = sim.getProbOfBestHandType(HandType.STRAIGHT),
            flushProb = sim.getProbOfBestHandType(HandType.FLUSH),
            fullHouseProb = sim.getProbOfBestHandType(HandType.FULL_HOUSE),
            quadrupleProb = sim.getProbOfBestHandType(HandType.QUADRUPLE),
            straightFlushProb = sim.getProbOfBestHandType(HandType.STRAIGHT_FLUSH)
        )
    )
}

private fun calculateExpectedValue(winProb: Double, cashToWin: Long, cashToLose: Long): Long =
    (winProb * cashToWin - (1 - winProb) * cashToLose).roundToLong()

private fun calculateOptimumBet(winProb: Double, cashToWin: Long): Long =
    (winProb * cashToWin).div(1 - winProb).roundToLong()

private fun List<MatchResult>.getProbOfMyHandType(handType: HandType): Double =
    this.map { it.myHand }.count { it == handType }.div(this.size.toDouble())

private fun List<MatchResult>.getProbOfBestHandType(handType: HandType): Double =
    this.map { it.bestHandType }.count { it == handType }.div(this.size.toDouble())
