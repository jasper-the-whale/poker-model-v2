package poker.model.domain

enum class HandType(val initialScoreValue: Long) {
    HIGH_CARD(100_000L),
    PAIR(200_000L),
    TWO_PAIR(300_000L),
    TRIPLE(400_000L),
    STRAIGHT(500_000L),
    FLUSH(600_000L),
    FULL_HOUSE(700_000L),
    QUADRUPLE(800_000L),
    STRAIGHT_FLUSH(900_000L)
}
