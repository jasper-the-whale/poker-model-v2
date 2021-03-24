package poker.model.simulation

import poker.model.EventConfiguration
import poker.model.domain.Card
import poker.model.domain.HandType
import poker.model.domain.MatchOutcome
import poker.model.domain.PlayerHandScore
import poker.model.ranking.handRanking
import poker.model.transformer.TableDetails

class MatchSimulator(
    private val eventConfig: EventConfiguration = EventConfiguration(),
) {
    fun simulate(tableDetails: TableDetails, deck: List<Card>): MatchOutcome {
        val allTableCards = getSimulatedTableCards(tableDetails,deck)
        val opponentHands = getRandomOpponentHands(tableDetails, deck)

        val bestOpponentHand = opponentHands.maxByOrNull { it.handScore }
            ?: PlayerHandScore(0, HandType.HIGH_CARD)

        return MatchOutcome(tableDetails.myCards.plus(allTableCards).handRanking(), bestOpponentHand)
    }

    private fun getSimulatedTableCards(tableDetails: TableDetails, deck: List<Card>) =
        tableDetails.tableCards.plus(
            (0 until eventConfig.maxTableCards - tableDetails.tableCards.size).toList().map { deck[it] })

    private fun getRandomOpponentHands(tableDetails: TableDetails, deck: List<Card>): List<PlayerHandScore> =
        (0 until tableDetails.totalPlayers).toList().map {
            listOf(
                deck[2 * it + eventConfig.maxTableCards - tableDetails.tableCards.size],
                deck[2 * it + eventConfig.maxTableCards - tableDetails.tableCards.size + 1]
            ).plus(tableDetails.tableCards)
        }.map { it.handRanking() }

}


