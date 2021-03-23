package poker.model.ranking

import com.marcinmoskala.math.pow
import poker.model.domain.Card
import poker.model.domain.HandType
import poker.model.domain.PlayerHandScore

fun List<Card>.handRanking(): PlayerHandScore {
    return when {
        this.isStraightFlush() -> PlayerHandScore(
            getHandScore(HandType.STRAIGHT_FLUSH.initialScoreValue, this),
            HandType.STRAIGHT_FLUSH
        )
        this.isQuadruple() -> PlayerHandScore(
            getHandScore(HandType.QUADRUPLE.initialScoreValue, this),
            HandType.QUADRUPLE
        )
        this.isFullHouse() -> PlayerHandScore(
            getHandScore(HandType.FULL_HOUSE.initialScoreValue, this),
            HandType.FULL_HOUSE
        )
        this.isFlush() -> PlayerHandScore(
            getHandScore(HandType.FLUSH.initialScoreValue, this),
            HandType.FLUSH
        )
        this.isStraight() -> PlayerHandScore(
            getHandScore(HandType.STRAIGHT.initialScoreValue, this),
            HandType.STRAIGHT
        )
        this.isTriple() -> PlayerHandScore(
            getHandScore(HandType.TRIPLE.initialScoreValue, this),
            HandType.TRIPLE
        )
        this.isTwoPair() -> PlayerHandScore(
            getHandScore( HandType.TWO_PAIR.initialScoreValue, this),
            HandType.TWO_PAIR
        )
        this.isPair() -> PlayerHandScore(
            getHandScore(HandType.PAIR.initialScoreValue, this),
            HandType.PAIR
        )
        else -> PlayerHandScore(
            getHandScore(HandType.HIGH_CARD.initialScoreValue, this),
            HandType.HIGH_CARD
        )
    }
}

private fun getHandScore(initialHandValue: Long, hand: List<Card>): Long {
    val cardsGroupedByValue = hand.groupBy { it.weight.name }.map { it.value }
    return initialHandValue + cardsGroupedByValue.map { pictureValueGroup ->
        val totalNumber = pictureValueGroup.size
        pictureValueGroup.map { card -> card.weight.weightNumber.pow(totalNumber) }.sum()
    }.sum().toLong()
}
