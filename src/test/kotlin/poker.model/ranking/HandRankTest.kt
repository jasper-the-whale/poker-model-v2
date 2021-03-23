package poker.model.ranking

import org.junit.jupiter.api.Assertions.assertEquals
import poker.model.domain.Card
import poker.model.domain.Weight
import poker.model.domain.Suit
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import poker.model.domain.HandType
import poker.model.domain.PlayerHandScore

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
                    PlayerHandScore(handScore = 900_242, handType = HandType.STRAIGHT_FLUSH),
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
                    PlayerHandScore(handScore = 840_017, handType = HandType.QUADRUPLE),
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
                    PlayerHandScore(handScore = 703_249, handType = HandType.FULL_HOUSE),
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
                    PlayerHandScore(handScore = 600_047, handType = HandType.FLUSH),
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
                    PlayerHandScore(handScore = 500_242, handType = HandType.STRAIGHT),
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
                    PlayerHandScore(handScore = 403_021, handType = HandType.TRIPLE),
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
                    PlayerHandScore(handScore = 300_249, handType = HandType.TWO_PAIR),
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
                    PlayerHandScore(handScore = 200_226, handType = HandType.PAIR),
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
                    PlayerHandScore(handScore = 100_049, handType = HandType.HIGH_CARD),
                    aHand.handRanking()
                )
            }
        }
    }
}
