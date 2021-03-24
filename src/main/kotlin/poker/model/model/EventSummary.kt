package poker.model.model

data class EventSummary(
    val eventDetails: EventDetails,
    val myHandTypeProbabilities: HandProbabilities,
    val bestHandTypeProbabilities: HandProbabilities
)

data class EventDetails(
    val totalSimulations: Long,
    val winProb: Double,
    val expectedValue: Long,
    val optimumBet: Long,
)

data class HandProbabilities(
    val highCard: Double,
    val pair: Double,
    val twoPair: Double,
    val triple: Double,
    val straight: Double,
    val flush: Double,
    val fullHouse: Double,
    val quadruple: Double,
    val straightFlush: Double
)
