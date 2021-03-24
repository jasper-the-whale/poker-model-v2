package poker.model.domain

data class Response(
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
    val highCardProb: Double,
    val pairProb: Double,
    val twoPairProb: Double,
    val tripleProb: Double,
    val straightProb: Double,
    val flushProb: Double,
    val fullHouseProb: Double,
    val quadrupleProb: Double,
    val straightFlushProb: Double
)
