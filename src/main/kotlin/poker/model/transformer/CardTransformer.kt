package poker.model.transformer

import poker.model.MatchConfiguration
import poker.model.model.Card
import poker.model.model.Suit
import poker.model.model.TableDetails
import poker.model.model.Weight

class CardTransformer(
    private val matchConfig: MatchConfiguration = MatchConfiguration()
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
        val suit = Suit.getSuitFromNumber(this.rem(matchConfig.totalCards))
        val weight = Weight.getValueFromNumber(this.div(matchConfig.totalSuits) + 2)

        return Card(suit, weight)
    }
}