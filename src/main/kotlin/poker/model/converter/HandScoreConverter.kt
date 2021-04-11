package poker.model.converter

import com.marcinmoskala.math.pow
import poker.model.model.Card
import poker.model.model.HandType
import poker.model.model.HandScore
import poker.model.ranking.isFlush
import poker.model.ranking.isFullHouse
import poker.model.ranking.isPair
import poker.model.ranking.isQuadruple
import poker.model.ranking.isStraight
import poker.model.ranking.isStraightFlush
import poker.model.ranking.isTriple
import poker.model.ranking.isTwoPair

class HandScoreConverter {
    fun convertToHandScore(cards: List<Card>): HandScore {
        val handRank =  cards.getHandType()
        val cardWeightValue = cards.getHandWeightValue()

        return HandScore(handRank.initalHandScoreValue + cardWeightValue, handRank)
    }

    private fun List<Card>.getHandType(): HandType =
        when {
            this.isStraightFlush() -> HandType.STRAIGHT_FLUSH
            this.isQuadruple() -> HandType.QUADRUPLE
            this.isFullHouse() -> HandType.FULL_HOUSE
            this.isFlush() -> HandType.FLUSH
            this.isStraight() -> HandType.STRAIGHT
            this.isTriple() -> HandType.TRIPLE
            this.isTwoPair() -> HandType.TWO_PAIR
            this.isPair() -> HandType.PAIR
            else -> HandType.HIGH_CARD
        }

    private fun List<Card>.getHandWeightValue(): Int =
        this.groupByCardWeight().map { pictureValueGroup ->
            pictureValueGroup.value.pictureRankingValue()
        }.sum()

    private fun List<Card>.groupByCardWeight()  =
        this.groupBy { it.weight.name }

    private fun List<Card>.pictureRankingValue(): Int =
        this.map { card -> card.weight.weightNumber.pow(this.size) }.sum()
}

