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
    fun getMatchResults(tableDetails: TableDetails): List<MatchResult> =
        (0 until eventConfig.totalSims).toList().map {
            val deck = eventConfig.getNewDeck()
            val filteredDeck = deck remove (tableDetails.myCards + tableDetails.tableCards)
            val shuffledDeck = filteredDeck.shuffled()

            val simulatedGame = matchSimulator.simulate(tableDetails, shuffledDeck)

            toMatchResult(tableDetails, simulatedGame)
        }

    private infix fun List<Card>.remove(knowCards: List<Card>) =
        this.filter { !knowCards.contains(it) }

    private fun toMatchResult(tableDetails: TableDetails, matchOutcome: MatchOutcome) =
        MatchResult(
            tableDetails = tableDetails,
            playerHand = matchOutcome.playerHand.type,
            bestHandScore = matchOutcome.getBestHandScore(),
            bestHandType = matchOutcome.getBestHandType(),
            isHandWinning = matchOutcome.isMyHandBest()
        )

    private fun MatchOutcome.isMyHandBest(): Boolean =
        this.playerHand.value > this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandScore(): Long =
        if (this.playerHand.value > this.bestOpponentHand.value) this.playerHand.value
        else this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandType(): HandType =
        if (this.playerHand.value > this.bestOpponentHand.value) this.playerHand.type
        else this.bestOpponentHand.type
}
