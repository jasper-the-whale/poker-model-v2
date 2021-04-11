package poker.model.transformer

import poker.model.MatchConfiguration
import poker.model.model.Card
import poker.model.model.TableSummary
import poker.model.model.InvalidTableSummary
import poker.model.model.Suit
import poker.model.model.TableDetails
import poker.model.model.ValidTableSummary
import poker.model.model.Weight

class CardTransformer(
    private val matchConfig: MatchConfiguration = MatchConfiguration()
) {
    fun toTableDetails(
        totalPlayers: Int,
        pot: Long,
        betToLose: Long,
        myCards: List<Int>,
        tableCards: List<Int>
    ): TableSummary =
        try {
            val myCards = myCards.map { cardValue -> convertToCard(cardValue) }
            val tableCards = tableCards.map { cardValue -> convertToCard(cardValue) }

            ValidTableSummary(TableDetails(totalPlayers, pot, betToLose, myCards, tableCards))
        } catch (e: NoSuchElementException) {
            println("Invalid card inputs")
            InvalidTableSummary
        }

    fun convertToCard(cardNumber: Int): Card {
        val suitValue = cardNumber.rem(matchConfig.totalSuits)
        val weightValue = (cardNumber - 1).div(matchConfig.totalSuits) + 2

        val suit = Suit.getSuitFromNumber(suitValue)
        val weight = Weight.getWeightFromNumber(weightValue)

        return Card(suit, weight)
    }
}
