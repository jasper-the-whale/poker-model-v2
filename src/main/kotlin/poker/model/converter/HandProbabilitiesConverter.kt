package poker.model.converter

import poker.model.model.HandProbabilities
import poker.model.model.HandType
import poker.model.model.MatchRecord
import poker.model.model.MatchResults

fun convertToHandProbabilities(isPlayer: Boolean, matchResults: MatchResults): HandProbabilities {
    val totalMatches = matchResults.matchRecords.size
    val handTypesGrouped = groupByHandType(matchResults.matchRecords)

    val winningHands = if (isPlayer) handTypesGrouped.mapToWinningHands() else handTypesGrouped.mapToLosingHands()

    return HandProbabilities(
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
}

private fun groupByHandType(matchResults: List<MatchRecord>): Map<HandType, List<MatchRecord>> =
    matchResults.groupBy { it.playerHandType }

private fun Map<HandType, List<MatchRecord>>.mapToWinningHands(): Map<HandType, Int> =
    this.mapValues { it.value.filter { matchResult -> matchResult.isPlayerHandWinning }.size }

private fun Map<HandType, List<MatchRecord>>.mapToLosingHands(): Map<HandType, Int> =
    this.mapValues { it.value.filter { matchResult -> !matchResult.isPlayerHandWinning }.size }

private fun probabilityOfHandType(winningHands: Map<HandType, Int>, handType: HandType, totalMatches: Int): Double {
    val totalSims = totalMatches.toDouble()
    val totalWins = winningHands[handType]
    return totalWins?.div(totalSims) ?: 0.0
}
