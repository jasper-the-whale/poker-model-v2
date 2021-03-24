package poker.model.transformer

import poker.model.EventConfiguration
import poker.model.domain.Card
import poker.model.domain.Suit
import poker.model.domain.Weight

class CardTransformer(
    private val eventConfig: EventConfiguration = EventConfiguration()
) {
    fun toTableDetails(totalPlayers: Int, pot: Long, betToLose: Long, myCards: List<Int>, tableCards: List<Int>) =
        TableDetails(
            totalPlayers = totalPlayers,
            pot = pot,
            betToLose = betToLose,
            myCards = myCards.map { cardValue -> cardValue.toCard() },
            tableCards = tableCards.map { cardValue -> cardValue.toCard() }
        )

    private fun Int.toCard(): Card {
        val suit = Suit.getSuitFromNumber(this.rem(eventConfig.totalCards))
        val weight = Weight.getValueFromNumber(this.div(eventConfig.totalSuits) + 2)

        return Card(suit, weight)
    }
}