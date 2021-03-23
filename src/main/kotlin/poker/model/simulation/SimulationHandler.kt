package poker.model.simulation

import poker.model.domain.ApiResponse
import poker.model.domain.Card
import poker.model.domain.MatchResult
import poker.model.domain.Suit
import poker.model.domain.Weight

private const val TOTAL_CARDS = 52
private const val TOTAL_SUITS = 4

fun calculateOutcomeProbabilities(
    totalSims: Long,
    totalPlayers: Int,
    pot: Long,
    betToLose: Long,
    myCards: List<Card>,
    tableCards: List<Card>
): ApiResponse {
    val deck: List<Card> = getDeckOfCards().filter { !myCards.contains(it) && !tableCards.contains(it) }
    val sim = getSimulatedMatches(totalSims, totalPlayers, deck, myCards, tableCards)

    return getMatchSummary(sim, pot, betToLose)
}

private fun getSimulatedMatches(
    totalSims: Long,
    totalPlayers: Int,
    deck: List<Card>,
    myCards: List<Card>,
    tableCards: List<Card>
): List<MatchResult> =
    (0 until totalSims).toList().map {
        val simulatedGame = simulateMatch(myCards, deck, tableCards, totalPlayers)
        MatchResult(
            myHand = simulatedGame.myHand.handType,
            bestHandScore = simulatedGame.getBestHandScore(),
            bestHandType = simulatedGame.getBestHandType(),
            isHandWinning = simulatedGame.isMyHandBest()
        )
    }

private fun getDeckOfCards(): List<Card> {
    return (0 until TOTAL_CARDS).toList().map {
        val suit = Suit.getSuitFromNumber(it.rem(TOTAL_SUITS))
        val weight = Weight.getValueFromNumber(it.div(TOTAL_SUITS) + 2)
        Card(suit, weight)
    }
}

fun Int.translateToCard(): Card {
    val suit = Suit.getSuitFromNumber(this.rem(TOTAL_SUITS))
    val weight = Weight.getValueFromNumber(this.div(TOTAL_SUITS) + 2)
    return Card(suit, weight)
}
