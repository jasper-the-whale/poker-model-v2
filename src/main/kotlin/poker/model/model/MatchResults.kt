package poker.model.model

data class MatchResults(
    val tableDetails: TableDetails,
    val matchRecords: List<MatchRecord>
)

data class MatchRecord(
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
