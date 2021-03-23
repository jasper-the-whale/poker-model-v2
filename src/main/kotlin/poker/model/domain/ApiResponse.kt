package poker.model.domain

data class ApiResponse(
    val totalSimulations: Long,
    val winProb: Double,
    val expectedValue: Long,
    val optimumBet: Long,
    val myHandTypeProbabilities: HandProbabilities,
    val bestHandTypeProbabilities: HandProbabilities
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
