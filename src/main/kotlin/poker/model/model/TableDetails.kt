package poker.model.model

data class TableDetails(
    val totalPlayers: Int,
    val pot: Long,
    val betToLose: Long,
    val myCards: List<Card>,
    val tableCards: List<Card>,
)