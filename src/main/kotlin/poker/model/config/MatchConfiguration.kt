package poker.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import poker.model.model.Card
import poker.model.model.Suit
import poker.model.model.Weight

@ConfigurationProperties(prefix = "match")
@ConstructorBinding
data class MatchConfiguration(
    val totalCards: Int = 52,
    val totalSuits: Int = 4,
    val totalSims: Long = 1000,
    val maxTableCards: Int = 5
)
