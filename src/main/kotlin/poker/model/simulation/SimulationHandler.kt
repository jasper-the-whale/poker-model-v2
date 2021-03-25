package poker.model.simulation

import poker.model.MatchConfiguration
import poker.model.model.Card
import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.MatchResults
import poker.model.model.MatchRecord
import poker.model.model.TableDetails


//TODO add a new class simulation concert which should take the simulated game and turn it into a MatchResult
class SimulationHandler(
    private val matchConfig: MatchConfiguration = MatchConfiguration(),
    private val matchSimulator: MatchSimulator = MatchSimulator()
) {
    fun getMatchResults(tableDetails: TableDetails): MatchResults {
        val matchRecords = (0 until matchConfig.totalSims).toList().map {
            val deck = matchConfig.getNewDeck()
            val filteredDeck = deck remove (tableDetails.myCards + tableDetails.tableCards)
            val shuffledDeck = filteredDeck.shuffled()

            val simulatedGame = matchSimulator.simulate(tableDetails, shuffledDeck)

            toMatchRecord(simulatedGame)
        }
        return MatchResults(tableDetails, matchRecords)
    }

    private infix fun List<Card>.remove(knownCards: List<Card>) =
        this.filter { !knownCards.contains(it) }

    private fun toMatchRecord(matchOutcome: MatchOutcome) =
        MatchRecord(
            playerHand = matchOutcome.playerHand.type,
            bestHandScore = matchOutcome.getBestHandScore(),
            bestHandType = matchOutcome.getBestHandType(),
            isHandWinning = matchOutcome.isMyHandBest()
        )

    private fun MatchOutcome.getBestHandScore(): Long =
        if (this.playerHand.value > this.bestOpponentHand.value) {
            this.playerHand.value
        } else this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandType(): HandType =
        if (this.playerHand.value > this.bestOpponentHand.value) {
            this.playerHand.type
        } else this.bestOpponentHand.type

    private fun MatchOutcome.isMyHandBest(): Boolean =
        this.playerHand.value > this.bestOpponentHand.value
}
