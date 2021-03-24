package poker.model.simulation

import poker.model.EventConfiguration
import poker.model.model.Card
import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.HandScore
import poker.model.converter.HandScoreConverter
import poker.model.transformer.TableDetails

private val DEFAULT_HAND = HandScore(0, HandType.HIGH_CARD)

class MatchSimulator(
    private val eventConfig: EventConfiguration = EventConfiguration(),
    private val handScoreConverter: HandScoreConverter = HandScoreConverter()
) {
    fun simulate(tableDetails: TableDetails, deck: List<Card>): MatchOutcome {
        val allTableCards = getSimulatedTableCards(tableDetails,deck)

        val playerHand = tableDetails.myCards.plus(allTableCards)
        val playerHandScore = handScoreConverter.convertToHandScore(playerHand)

        val opponentCards = getSimulatedOpponentHands(tableDetails, deck)
        val opponentHands = opponentCards.map { it.plus(allTableCards) }
        val opponentHandScores = opponentHands.map { handScoreConverter.convertToHandScore(it) }

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
