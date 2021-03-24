package poker.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import poker.model.domain.Card
import poker.model.domain.Suit
import poker.model.domain.Weight

@ConfigurationProperties(prefix = "event")
@ConstructorBinding
data class EventConfiguration(
    val totalCards: Int = 52,
    val totalSuits: Int = 4,
    val totalSims: Long = 1000,
    val maxTableCards: Int = 5
) {
    fun getNewDeck(): List<Card> {
        return (0 until totalCards).toList().map {
            val suit = Suit.getSuitFromNumber(it.rem(totalSuits))
            val weight = Weight.getValueFromNumber(it.div(totalSuits) + 2)
            Card(suit, weight)
        }.shuffled()
    }
}
