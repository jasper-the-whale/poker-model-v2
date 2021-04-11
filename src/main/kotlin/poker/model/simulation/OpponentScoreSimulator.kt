package poker.model.simulation

import poker.model.model.Card
import poker.model.model.HandScore
import poker.model.model.TableDetails

class OpponentScoreSimulator: HandScoreSimulator() {

    override fun simulateHandScore(tableDetails: TableDetails, deck: List<Card>): HandScore {
        val tableCards = remainingTableCards(tableDetails, deck)

        val opponentCards = someOpponentCards(tableDetails, deck)
        val opponentHands = opponentCards.map { it.mergeCards(tableCards) }
        val opponentHandScores = opponentHands.map { handScoreConverter.convertToHandScore(it) }

        return opponentHandScores.maxByOrNull { it.value } ?: defaultHandScore
    }

    private fun someOpponentCards(tableDetails: TableDetails, deck: List<Card>): List<List<Card>> =
        (0 until tableDetails.totalPlayers).toList().map { opponentIndex ->
            val cardIndex = nextCardIndex(tableDetails, opponentIndex)

            listOf(deck[cardIndex], deck[cardIndex + 1])
        }

    private fun nextCardIndex(tableDetails: TableDetails, opponentIndex: Int) =
        (2 * opponentIndex) + (matchConfig.maxTableCards - tableDetails.tableCards.size)
}