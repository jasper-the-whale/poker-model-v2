package poker.model.ranking

import poker.model.domain.Card
import poker.model.domain.Weight
import poker.model.domain.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class HandResultsTest {
    private val muckHand = listOf(
        Card(Suit.SPADE, Weight.TEN),
        Card(Suit.HEART, Weight.KING),
        Card(Suit.DIAMOND, Weight.FOUR),
        Card(Suit.DIAMOND, Weight.FIVE),
        Card(Suit.CLUB, Weight.TWO),
        Card(Suit.CLUB, Weight.THREE),
        Card(Suit.CLUB, Weight.QUEEN)
    )

    @Nested
    inner class isPair {
        @Test
        fun `should return true when there is a pair`() {
            val aHand = listOf<Card>(
                Card(Suit.SPADE, Weight.TEN),
                Card(Suit.HEART, Weight.TEN),
                Card(Suit.DIAMOND, Weight.FOUR),
                Card(Suit.DIAMOND, Weight.FIVE),
                Card(Suit.CLUB, Weight.TWO),
                Card(Suit.CLUB, Weight.THREE),
                Card(Suit.CLUB, Weight.QUEEN)
            )
            assertEquals(true, aHand.isPair())
        }

        @Test
        fun `should return false when there is not a pair`() {
            assertEquals(false, muckHand.isPair())
        }
    }

    @Nested
    inner class isTwoPair {
        @Test
        fun `should return true when there is a two pair`() {
            val aHand = listOf<Card>(
                Card(Suit.SPADE, Weight.TEN),
                Card(Suit.HEART, Weight.TEN),
                Card(Suit.DIAMOND, Weight.FOUR),
                Card(Suit.SPADE, Weight.FOUR),
                Card(Suit.CLUB, Weight.TWO),
                Card(Suit.CLUB, Weight.THREE),
                Card(Suit.CLUB, Weight.QUEEN)
            )
            assertEquals(true, aHand.isTwoPair())
        }

        @Test
        fun `should return false when there is not a two pair`() {
            assertEquals(false, muckHand.isTwoPair())
        }
    }

    @Nested
    inner class isTriple {
        @Test
        fun `should return true when there is a three of a kind`() {
            val aHand = listOf<Card>(
                Card(Suit.SPADE, Weight.TEN),
                Card(Suit.HEART, Weight.TEN),
                Card(Suit.DIAMOND, Weight.TEN),
                Card(Suit.SPADE, Weight.FOUR),
                Card(Suit.CLUB, Weight.TWO),
                Card(Suit.CLUB, Weight.THREE),
                Card(Suit.CLUB, Weight.QUEEN)
            )

            assertEquals(true, aHand.isTriple())
        }

        @Test
        fun `should return false when there is not a three of a kind`() {
            assertEquals(false, muckHand.isTriple())
        }
    }

    @Nested
    inner class isStraight {
        @Test
        fun `should return true when there is a straight`() {
            val aHand = listOf<Card>(
                Card(Suit.SPADE, Weight.JACK),
                Card(Suit.HEART, Weight.NINE),
                Card(Suit.DIAMOND, Weight.TEN),
                Card(Suit.DIAMOND, Weight.EIGHT),
                Card(Suit.CLUB, Weight.QUEEN),
                Card(Suit.CLUB, Weight.TWO),
                Card(Suit.CLUB, Weight.TEN)
            )

            assertEquals(true, aHand.isStraight())
        }

        @Test
        fun `should return false when there is not a straight`() {
            assertEquals(false, muckHand.isFlush())
        }
    }

    @Nested
    inner class isFlush {
        @Test
        fun `should return true when there is a flush`() {
            val aHand = listOf<Card>(
                Card(Suit.DIAMOND, Weight.FOUR),
                Card(Suit.DIAMOND, Weight.FIVE),
                Card(Suit.CLUB, Weight.TEN),
                Card(Suit.CLUB, Weight.JACK),
                Card(Suit.CLUB, Weight.TWO),
                Card(Suit.CLUB, Weight.THREE),
                Card(Suit.CLUB, Weight.QUEEN)
            )
            assertEquals(true, aHand.isFlush())
        }

        @Test
        fun `should return false when there is not a flush`() {
            assertEquals(false, muckHand.isFlush())
        }
    }

    @Nested
    inner class isFullHouse {
        @Test
        fun `should return true when there is a full house`() {
            val aHand = listOf<Card>(
                Card(Suit.DIAMOND, Weight.TEN),
                Card(Suit.SPADE, Weight.TEN),
                Card(Suit.CLUB, Weight.TEN),
                Card(Suit.DIAMOND, Weight.JACK),
                Card(Suit.SPADE, Weight.JACK),
                Card(Suit.CLUB, Weight.THREE),
                Card(Suit.CLUB, Weight.FOUR)
            )
            assertEquals(true, aHand.isFullHouse())
        }

        @Test
        fun `should return false when there is not a full house`() {
            assertEquals(false, muckHand.isFullHouse())
        }
    }

    @Nested
    inner class isQuadruple {
        @Test
        fun `should return true when there is a four of a kind`() {
            val aHand = listOf<Card>(
                Card(Suit.SPADE, Weight.TEN),
                Card(Suit.HEART, Weight.TEN),
                Card(Suit.DIAMOND, Weight.TEN),
                Card(Suit.CLUB, Weight.TEN),
                Card(Suit.SPADE, Weight.TWO),
                Card(Suit.CLUB, Weight.THREE),
                Card(Suit.DIAMOND, Weight.QUEEN)
            )

            assertEquals(true, aHand.isQuadruple())
        }

        @Test
        fun `should return false when there is not a four of a kind`() {
            assertEquals(false, muckHand.isQuadruple())
        }
    }

    @Nested
    inner class isStraightFlush {
        @Test
        fun `should return true when there is a straight flush`() {
            val aHand = listOf<Card>(
                Card(Suit.SPADE, Weight.JACK),
                Card(Suit.SPADE, Weight.NINE),
                Card(Suit.DIAMOND, Weight.SEVEN),
                Card(Suit.SPADE, Weight.EIGHT),
                Card(Suit.SPADE, Weight.QUEEN),
                Card(Suit.CLUB, Weight.SIX),
                Card(Suit.SPADE, Weight.TEN)
            )

            assertEquals(true, aHand.isStraightFlush())
        }

        @Test
        fun `should return false when there is not a straight flush`() {
            assertEquals(false, muckHand.isStraightFlush())
        }
    }
}
