package poker.model.converter

import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.MatchRecord

class MatchRecordConverter {
    fun convertToMatchRecord(matchOutcome: MatchOutcome) =
        MatchRecord(
            playerHand = matchOutcome.playerHand.type,
            bestHandScore = matchOutcome.getBestHandScore(),
            bestHandType = matchOutcome.getBestHandType(),
            isHandWinning = matchOutcome.isMyHandBest()
        )

    private fun MatchOutcome.getBestHandScore(): Long =
        if (this.playerHand.value > this.bestOpponentHand.value) {
            this.playerHand.value
        } else this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandType(): HandType =
        if (this.playerHand.value > this.bestOpponentHand.value) {
            this.playerHand.type
        } else this.bestOpponentHand.type

    private fun MatchOutcome.isMyHandBest(): Boolean =
        this.playerHand.value > this.bestOpponentHand.value
}