package poker.model.simulation

import poker.model.model.Card
import poker.model.model.HandScore
import poker.model.model.TableDetails

class PlayerScoreSimulator : HandScoreSimulator() {

    override fun simulateHandScore(tableDetails: TableDetails, deck: List<Card>): HandScore {
        val tableCards = remainingTableCards(tableDetails, deck)
        val playerHand = tableDetails.myCards.mergeCards(tableCards)

        return handScoreConverter.convertToHandScore(playerHand)
    }
}