package poker.model.simulation

import poker.model.EventConfiguration
import poker.model.model.Card
import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.MatchResult
import poker.model.transformer.TableDetails

class SimulationHandler(
    private val eventConfig: EventConfiguration = EventConfiguration(),
    private val matchSimulator: MatchSimulator = MatchSimulator()
) {
    fun getMatchResults(tableDetails: TableDetails): List<MatchResult> {
        val newDeck = eventConfig.getNewDeck()
        val filteredDeck = newDeck removedKnowCards (tableDetails.myCards + tableDetails.tableCards)

        return getSimulatedMatches(tableDetails, filteredDeck)

        //return getMatchSummary(sim, tableDetails)
    }

    private fun getSimulatedMatches(tableDetails: TableDetails, deck: List<Card>): List<MatchResult> =
        (0 until eventConfig.totalSims).toList().map {
            val shuffledDeck = deck.shuffled()
            val simulatedGame = matchSimulator.simulate(tableDetails, shuffledDeck)

            MatchResult(
                playerHand = simulatedGame.playerHand.type,
                bestHandScore = simulatedGame.getBestHandScore(),
                bestHandType = simulatedGame.getBestHandType(),
                isHandWinning = simulatedGame.isMyHandBest()
            )
        }

    private infix fun List<Card>.removedKnowCards(knowCards: List<Card>) =
        this.filter { !knowCards.contains(it) }

    private fun MatchOutcome.isMyHandBest(): Boolean =
        this.playerHand.value > this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandScore(): Long =
        if (this.playerHand.value > this.bestOpponentHand.value) this.playerHand.value
        else this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandType(): HandType =
        if (this.playerHand.value > this.bestOpponentHand.value) this.playerHand.type
        else this.bestOpponentHand.type
}
