package poker.model.domain

data class Card(
    val suit: Suit,
    val weight: Weight
)

enum class Suit(val suitNumber: Int, val suitString: String) {
    HEART(0, "H"),
    DIAMOND(1,"D"),
    SPADE(2, "S"),
    CLUB(3, "C");

    companion object {
        fun getSuitFromNumber(number: Int): Suit =
            values().first { it.suitNumber == number }
    }
}

enum class Weight(val weightNumber: Int, val weightString: String) {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");

    companion object {
        fun getValueFromNumber(number: Int): Weight =
            values().first { it.weightNumber == number }
    }
}
