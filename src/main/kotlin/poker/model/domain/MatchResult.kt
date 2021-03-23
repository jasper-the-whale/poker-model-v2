package poker.model.domain

data class MatchResult(
    val myHand: HandType,
    val bestHandScore: Long,
    val bestHandType: HandType,
    val isHandWinning: Boolean
)

data class MatchOutcome(
    val myHand: PlayerHandScore,
    val bestOpponentHand: PlayerHandScore
)

data class PlayerHandScore(
    val handScore: Long,
    val handType: HandType
)
