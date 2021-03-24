package poker.model.simulation

import poker.model.EventConfiguration
import poker.model.model.Card
import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.HandScore
import poker.model.ranking.handRanking
import poker.model.transformer.TableDetails

private val DEFAULT_HAND = HandScore(0, HandType.HIGH_CARD)

class MatchSimulator(
    private val eventConfig: EventConfiguration = EventConfiguration(),
) {
    fun simulate(tableDetails: TableDetails, deck: List<Card>): MatchOutcome {
        val allTableCards = getSimulatedTableCards(tableDetails,deck)
        val playerHandScore = tableDetails.myCards.plus(allTableCards).handRanking()

        val opponentHands = getSimulatedOpponentHands(tableDetails, deck)
        val opponentHandScores = opponentHands.map { it.plus(tableDetails.tableCards).handRanking() }
        val bestOpponentHandScore = opponentHandScores.maxByOrNull { it.value } ?: DEFAULT_HAND

        return MatchOutcome(playerHandScore, bestOpponentHandScore)
    }

    private fun getSimulatedTableCards(tableDetails: TableDetails, deck: List<Card>) =
        tableDetails.tableCards.plus(
            (0 until eventConfig.maxTableCards - tableDetails.tableCards.size).toList().map { deck[it] })


    private fun getSimulatedOpponentHands(tableDetails: TableDetails, deck: List<Card>): List<List<Card>> =
        (0 until tableDetails.totalPlayers).toList().map { opponentIndex ->
            val cardIndex = nextCardIndex(tableDetails, opponentIndex)

            listOf(deck[cardIndex], deck[cardIndex + 1])
        }

    private fun nextCardIndex(tableDetails: TableDetails, opponentIndex: Int) =
        (2 * opponentIndex) + (eventConfig.maxTableCards - tableDetails.tableCards.size)
}
