package poker.model.model

data class MatchResults(
    val tableDetails: TableDetails,
    val matchRecords: List<MatchRecord>
)

data class MatchRecord(
    val playerHandType: HandType,
    val bestHandValue: Long,
    val bestHandType: HandType,
    val isPlayerHandWinning: Boolean
)

data class MatchOutcome(
    val playerHand: HandScore,
    val bestOpponentHand: HandScore
)

data class HandScore(
    val value: Long,
    val type: HandType
)
