package poker.model.ranking

import com.marcinmoskala.math.pow
import poker.model.model.Card
import poker.model.model.HandType
import poker.model.model.HandScore

fun List<Card>.handRanking(): HandScore {
    return when {
        this.isStraightFlush() -> HandScore(
            getHandScore(HandType.STRAIGHT_FLUSH.initialScoreValue, this),
            HandType.STRAIGHT_FLUSH
        )
        this.isQuadruple() -> HandScore(
            getHandScore(HandType.QUADRUPLE.initialScoreValue, this),
            HandType.QUADRUPLE
        )
        this.isFullHouse() -> HandScore(
            getHandScore(HandType.FULL_HOUSE.initialScoreValue, this),
            HandType.FULL_HOUSE
        )
        this.isFlush() -> HandScore(
            getHandScore(HandType.FLUSH.initialScoreValue, this),
            HandType.FLUSH
        )
        this.isStraight() -> HandScore(
            getHandScore(HandType.STRAIGHT.initialScoreValue, this),
            HandType.STRAIGHT
        )
        this.isTriple() -> HandScore(
            getHandScore(HandType.TRIPLE.initialScoreValue, this),
            HandType.TRIPLE
        )
        this.isTwoPair() -> HandScore(
            getHandScore( HandType.TWO_PAIR.initialScoreValue, this),
            HandType.TWO_PAIR
        )
        this.isPair() -> HandScore(
            getHandScore(HandType.PAIR.initialScoreValue, this),
            HandType.PAIR
        )
        else -> HandScore(
            getHandScore(HandType.HIGH_CARD.initialScoreValue, this),
            HandType.HIGH_CARD
        )
    }
}

//TODO sort this out
private fun getHandScore(initialHandValue: Long, hand: List<Card>): Long {
    val cardsGroupedByValue = hand.groupBy { it.weight.name }.map { it.value }
    val cardsWeightValue = cardsGroupedByValue.map { pictureValueGroup ->
        val cardsInGroup = pictureValueGroup.size
        pictureValueGroup.map { card -> card.weight.weightNumber.pow(cardsInGroup) }.sum()
    }.sum()

    return initialHandValue + cardsWeightValue
}
