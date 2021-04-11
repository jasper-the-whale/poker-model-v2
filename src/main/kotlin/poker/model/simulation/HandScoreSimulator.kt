package poker.model.simulation

import poker.model.MatchConfiguration
import poker.model.converter.HandScoreConverter
import poker.model.model.Card
import poker.model.model.HandScore
import poker.model.model.HandType
import poker.model.model.TableDetails

abstract class HandScoreSimulator {
    protected val matchConfig: MatchConfiguration = MatchConfiguration()
    protected val handScoreConverter: HandScoreConverter = HandScoreConverter()
    protected val defaultHandScore = HandScore(0, HandType.HIGH_CARD)

    open fun simulateHandScore(tableDetails: TableDetails, deck: List<Card>): HandScore {
        return defaultHandScore
    }

    protected fun List<Card>.mergeCards(tableCards: List<Card>): List<Card> {
        return this.plus(tableCards)
    }

    protected fun remainingTableCards(tableDetails: TableDetails, deck: List<Card>) =
        tableDetails.tableCards.mergeCards(
            (0 until matchConfig.maxTableCards - tableDetails.tableCards.size).toList().map { deck[it] })
}