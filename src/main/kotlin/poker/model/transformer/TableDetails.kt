package poker.model.transformer

import poker.model.domain.Card

data class TableDetails(
    val totalPlayers: Int,
    val pot: Long,
    val betToLose: Long,
    val myCards: List<Card>,
    val tableCards: List<Card>,
)