package poker.model.model

import poker.model.transformer.TableDetails

data class MatchResult(
    val tableDetails: TableDetails,
    val playerHand: HandType,
    val bestHandScore: Long,
    val bestHandType: HandType,
    val isHandWinning: Boolean
)

data class MatchOutcome(
    val playerHand: HandScore,
    val bestOpponentHand: HandScore
)

data class HandScore(
    val value: Long,
    val type: HandType
)
