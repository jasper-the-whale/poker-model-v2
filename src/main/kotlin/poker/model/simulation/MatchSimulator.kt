package poker.model.simulation

import poker.model.model.Card
import poker.model.model.MatchOutcome
import poker.model.model.TableDetails

class MatchSimulator(
    private val playerScoreSimulator: PlayerScoreSimulator = PlayerScoreSimulator(),
    private val opponentScoreSimulator: OpponentScoreSimulator = OpponentScoreSimulator()
) {
    fun simulate(tableDetails: TableDetails, deck: List<Card>): MatchOutcome {
        val playerHandScore = playerScoreSimulator.simulateHandScore(tableDetails, deck)
        val bestOpponentHandScore = opponentScoreSimulator.simulateHandScore(tableDetails, deck)

        return MatchOutcome(playerHandScore, bestOpponentHandScore)
    }
}
