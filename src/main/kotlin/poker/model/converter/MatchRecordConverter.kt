package poker.model.converter

import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.MatchRecord

class MatchRecordConverter {
    fun convertToMatchRecord(matchOutcome: MatchOutcome) =
        MatchRecord(
            playerHandType = matchOutcome.playerHand.type,
            bestHandValue = matchOutcome.getBestHandValue(),
            bestHandType = matchOutcome.getBestHandType(),
            isPlayerHandWinning = matchOutcome.isPlayerHandBest()
        )

    private fun MatchOutcome.getBestHandValue(): Long =
        if (this.playerHand.value > this.bestOpponentHand.value) {
            this.playerHand.value
        } else this.bestOpponentHand.value

    private fun MatchOutcome.getBestHandType(): HandType =
        if (this.playerHand.value > this.bestOpponentHand.value) {
            this.playerHand.type
        } else this.bestOpponentHand.type

    private fun MatchOutcome.isPlayerHandBest(): Boolean =
        this.playerHand.value > this.bestOpponentHand.value
}