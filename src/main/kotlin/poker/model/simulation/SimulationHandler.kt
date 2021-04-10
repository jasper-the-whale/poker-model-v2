package poker.model.simulation

import org.springframework.stereotype.Component
import poker.model.MatchConfiguration
import poker.model.converter.MatchRecordConverter
import poker.model.model.Card
import poker.model.model.MatchResults
import poker.model.model.Suit
import poker.model.model.TableDetails
import poker.model.model.Weight

@Component
class SimulationHandler(
    private val matchConfig: MatchConfiguration = MatchConfiguration(),
    private val matchSimulator: MatchSimulator = MatchSimulator(),
    private val matchRecordConverter: MatchRecordConverter = MatchRecordConverter(),
) {
    fun getMatchResults(tableDetails: TableDetails): MatchResults {
        val matchRecords = (0 until matchConfig.totalSims).toList().map {
            val deck = getNewDeck()
            val filteredDeck = deck remove (tableDetails.myCards + tableDetails.tableCards)
            val shuffledDeck = filteredDeck.shuffled()

            val matchOutcome = matchSimulator.simulate(tableDetails, shuffledDeck)

            matchRecordConverter.convertToMatchRecord(matchOutcome)
        }

        return MatchResults(tableDetails, matchRecords)
    }

    private infix fun List<Card>.remove(knownCards: List<Card>) =
        this.filter { !knownCards.contains(it) }

    fun getNewDeck(): List<Card> {
        return (0 until matchConfig.totalCards).toList().map {
            val suit = Suit.getSuitFromNumber(it.rem(matchConfig.totalSuits))
            val weight = Weight.getWeightFromNumber(it.div(matchConfig.totalSuits) + 2)
            Card(suit, weight)
        }
    }
}
