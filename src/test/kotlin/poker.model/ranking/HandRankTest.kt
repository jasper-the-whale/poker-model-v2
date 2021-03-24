package poker.model.ranking

import org.junit.jupiter.api.Assertions.assertEquals
import poker.model.model.Card
import poker.model.model.Weight
import poker.model.model.Suit
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import poker.model.model.HandType
import poker.model.model.HandScore

internal class HandRankTest {
    @Nested
    inner class handRanking {
        @Nested
        inner class `when list of cards is a straight flush` {
            @Test
            fun `should return a PlayerHandScore with handType as straight flush`() {
                val aHand = listOf(
                    Card(Suit.SPADE, Weight.JACK),
                    Card(Suit.SPADE, Weight.NINE),
                    Card(Suit.DIAMOND, Weight.TEN),
                    Card(Suit.SPADE, Weight.EIGHT),
                    Card(Suit.SPADE, Weight.QUEEN),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.SPADE, Weight.TEN)
                )

                assertEquals(
                    HandScore(value = 900_242, type = HandType.STRAIGHT_FLUSH),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a four of a kind` {
            @Test
            fun `should return a PlayerHandScore with handType as quadruple`() {
                val aHand = listOf<Card>(
                    Card(Suit.SPADE, Weight.TEN),
                    Card(Suit.HEART, Weight.TEN),
                    Card(Suit.DIAMOND, Weight.TEN),
                    Card(Suit.CLUB, Weight.TEN),
                    Card(Suit.SPADE, Weight.TWO),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.DIAMOND, Weight.QUEEN)
                )

                assertEquals(
                    HandScore(value = 840_017, type = HandType.QUADRUPLE),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a full house` {
            @Test
            fun `should return a PlayerHandScore with handType as full house`() {
                val aHand = listOf<Card>(
                    Card(Suit.DIAMOND, Weight.TEN),
                    Card(Suit.SPADE, Weight.TEN),
                    Card(Suit.CLUB, Weight.TEN),
                    Card(Suit.DIAMOND, Weight.JACK),
                    Card(Suit.SPADE, Weight.JACK),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.CLUB, Weight.FOUR)
                )

                assertEquals(
                    HandScore(value = 703_249, type = HandType.FULL_HOUSE),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a flush` {
            @Test
            fun `should return a PlayerHandScore with handType as flush`() {
                val aHand = listOf<Card>(
                    Card(Suit.DIAMOND, Weight.FOUR),
                    Card(Suit.DIAMOND, Weight.FIVE),
                    Card(Suit.CLUB, Weight.TEN),
                    Card(Suit.CLUB, Weight.JACK),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.CLUB, Weight.QUEEN)
                )

                assertEquals(
                    HandScore(value = 600_047, type = HandType.FLUSH),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a straight` {
            @Test
            fun `should return a PlayerHandScore with handType as straight`() {
                val aHand = listOf<Card>(
                    Card(Suit.SPADE, Weight.JACK),
                    Card(Suit.HEART, Weight.NINE),
                    Card(Suit.DIAMOND, Weight.TEN),
                    Card(Suit.DIAMOND, Weight.EIGHT),
                    Card(Suit.CLUB, Weight.QUEEN),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.CLUB, Weight.TEN)
                )

                assertEquals(
                    HandScore(value = 500_242, type = HandType.STRAIGHT),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a three of a kind` {
            @Test
            fun `should return a PlayerHandScore with handType as three of a kind`() {
                val aHand = listOf<Card>(
                    Card(Suit.SPADE, Weight.TEN),
                    Card(Suit.HEART, Weight.TEN),
                    Card(Suit.DIAMOND, Weight.TEN),
                    Card(Suit.SPADE, Weight.FOUR),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.CLUB, Weight.QUEEN)
                )

                assertEquals(
                    HandScore(value = 403_021, type = HandType.TRIPLE),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a two pair` {
            @Test
            fun `should return a PlayerHandScore with handType as two pair`() {
                val aHand = listOf<Card>(
                    Card(Suit.SPADE, Weight.TEN),
                    Card(Suit.HEART, Weight.TEN),
                    Card(Suit.DIAMOND, Weight.FOUR),
                    Card(Suit.SPADE, Weight.FOUR),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.CLUB, Weight.QUEEN)
                )

                assertEquals(
                    HandScore(value = 300_249, type = HandType.TWO_PAIR),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a pair` {
            @Test
            fun `should return a PlayerHandScore with handType as pair`() {
                val aHand = listOf<Card>(
                    Card(Suit.SPADE, Weight.TEN),
                    Card(Suit.HEART, Weight.TEN),
                    Card(Suit.DIAMOND, Weight.FOUR),
                    Card(Suit.DIAMOND, Weight.FIVE),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.CLUB, Weight.QUEEN)
                )

                assertEquals(
                    HandScore(value = 200_226, type = HandType.PAIR),
                    aHand.handRanking()
                )
            }
        }

        @Nested
        inner class `when list of cards is a high card` {
            @Test
            fun `should return a PlayerHandScore with handType as high card`() {
                val aHand = listOf(
                    Card(Suit.SPADE, Weight.TEN),
                    Card(Suit.HEART, Weight.KING),
                    Card(Suit.DIAMOND, Weight.FOUR),
                    Card(Suit.DIAMOND, Weight.FIVE),
                    Card(Suit.CLUB, Weight.TWO),
                    Card(Suit.CLUB, Weight.THREE),
                    Card(Suit.CLUB, Weight.QUEEN)
                )

                assertEquals(
                    HandScore(value = 100_049, type = HandType.HIGH_CARD),
                    aHand.handRanking()
                )
            }
        }
    }
}
