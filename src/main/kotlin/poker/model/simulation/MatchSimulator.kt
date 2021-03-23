package poker.model.simulation

import poker.model.domain.Card
import poker.model.domain.HandType
import poker.model.domain.MatchOutcome
import poker.model.domain.PlayerHandScore
import poker.model.ranking.handRanking

private const val MAX_TABLE_CARDS = 5

fun simulateMatch(
    myCards: List<Card>, deck: List<Card>, currentTableCards: List<Card>, totalPlayers: Int
): MatchOutcome {
    val shuffledDeck = deck.shuffled()
    val simulatedTable = currentTableCards.plus(
        (0 until MAX_TABLE_CARDS - currentTableCards.size).toList()
            .map { shuffledDeck[it] })

    val opponentHands =
        getRandomOpponentHands(totalPlayers, shuffledDeck, currentTableCards, simulatedTable)
    val bestOpponentHand = opponentHands.maxBy { it.handScore }
        ?: PlayerHandScore(0, HandType.HIGH_CARD)

    return MatchOutcome(myCards.plus(simulatedTable).handRanking(), bestOpponentHand)
}

private fun getRandomOpponentHands(
    totalPlayers: Int, shuffledDeck: List<Card>, tableCards: List<Card>, table: List<Card>
): List<PlayerHandScore> =
    (0 until totalPlayers).toList().map {
        listOf(
            shuffledDeck[2 * it + MAX_TABLE_CARDS - tableCards.size],
            shuffledDeck[2 * it + MAX_TABLE_CARDS - tableCards.size + 1]
        ).plus(table)
    }.map { it.handRanking() }

fun MatchOutcome.isMyHandBest(): Boolean =
    this.myHand.handScore > this.bestOpponentHand.handScore

fun MatchOutcome.getBestHandScore(): Long =
    if (this.myHand.handScore > this.bestOpponentHand.handScore) this.myHand.handScore
    else this.bestOpponentHand.handScore

fun MatchOutcome.getBestHandType(): HandType =
    if (this.myHand.handScore > this.bestOpponentHand.handScore) this.myHand.handType
    else this.bestOpponentHand.handType
