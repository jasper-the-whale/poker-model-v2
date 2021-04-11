package poker.model.model

sealed class TableSummary

object InvalidTableSummary: TableSummary()

data class ValidTableSummary(val tableDetails: TableDetails): TableSummary()

data class TableDetails(
    val totalPlayers: Int,
    val pot: Long,
    val betToLose: Long,
    val myCards: List<Card>,
    val tableCards: List<Card>,
)