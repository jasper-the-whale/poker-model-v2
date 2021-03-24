package poker.model.simulation

import poker.model.EventConfiguration
import poker.model.domain.Card
import poker.model.domain.HandType
import poker.model.domain.MatchOutcome
import poker.model.domain.MatchResult
import poker.model.transformer.TableDetails

class SimulationHandler(
    private val eventConfig: EventConfiguration = EventConfiguration(),
    private val matchSimulator: MatchSimulator = MatchSimulator()
) {
    fun getMatchResults(tableDetails: TableDetails): List<MatchResult> {
        val newDeck = eventConfig.getNewDeck()
        val filteredDeck = newDeck removedKnowCards (tableDetails.myCards + tableDetails.tableCards)

        return getSimulatedMatches(tableDetails, filteredDeck)

        //return getMatchSummary(sim, tableDetails.pot, tableDetails.betToLose)
    }

    private fun getSimulatedMatches(tableDetails: TableDetails, deck: List<Card>): List<MatchResult> =
        (0 until eventConfig.totalSims).toList().map {
            val simulatedGame = matchSimulator.simulate(tableDetails, deck)

            MatchResult(
                myHand = simulatedGame.myHand.handType,
                bestHandScore = simulatedGame.getBestHandScore(),
                bestHandType = simulatedGame.getBestHandType(),
                isHandWinning = simulatedGame.isMyHandBest()
            )
        }

    private infix fun List<Card>.removedKnowCards(knowCards: List<Card>) =
        this.filter { !knowCards.contains(it) }

    private fun MatchOutcome.isMyHandBest(): Boolean =
        this.myHand.handScore > this.bestOpponentHand.handScore

    private fun MatchOutcome.getBestHandScore(): Long =
        if (this.myHand.handScore > this.bestOpponentHand.handScore) this.myHand.handScore
        else this.bestOpponentHand.handScore

    private fun MatchOutcome.getBestHandType(): HandType =
        if (this.myHand.handScore > this.bestOpponentHand.handScore) this.myHand.handType
        else this.bestOpponentHand.handType
}
