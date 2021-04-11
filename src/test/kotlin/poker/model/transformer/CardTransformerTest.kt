package poker.model.transformer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import poker.model.model.Card
import poker.model.model.InvalidTableSummary
import poker.model.model.Suit
import poker.model.model.TableDetails
import poker.model.model.ValidTableSummary
import poker.model.model.Weight

class CardTransformerTest {

    private val cardTransformer = CardTransformer()

    @Nested
    inner class toTableDetails {
        @Test
        fun `should return the table details when card inputs are valid`() {
            val actual = cardTransformer.toTableDetails(
                totalPlayers = 5,
                pot = 1000,
                betToLose = 250,
                myCards = listOf(30, 31),
                tableCards = listOf(29, 40, 48)
            )
            val expected = ValidTableSummary(
                TableDetails(
                    totalPlayers = 5,
                    pot = 1000,
                    betToLose = 250,
                    myCards = listOf(Card(Suit.SPADE, Weight.NINE), Card(Suit.CLUB, Weight.NINE)),
                    tableCards = listOf(
                        Card(Suit.DIAMOND, Weight.NINE),
                        Card(Suit.HEART, Weight.JACK),
                        Card(Suit.HEART, Weight.KING)
                    )
                )
            )

            assertEquals(expected, actual)
        }

        @Test
        fun `when invalid card number returns InvalidTableSummary`() {
            val actual = cardTransformer.toTableDetails(
                totalPlayers = 5,
                pot = 1000,
                betToLose = 250,
                myCards = listOf(100, 31),
                tableCards = listOf(29, 40, 48)
            )

            assertEquals(InvalidTableSummary, actual)
        }
    }

    @Nested
    inner class convertToCard {
        @Nested
        inner class `when request perameter is a valid number` {
            @Test
            fun `when number is 1 should return two of diamonds`() {
                assertEquals(Card(Suit.DIAMOND, Weight.TWO), cardTransformer.convertToCard(1))
            }

            @Test
            fun `when number is 2 should return two of spades`() {
                assertEquals(Card(Suit.SPADE, Weight.TWO), cardTransformer.convertToCard(2))
            }

            @Test
            fun `when number is 3 should return two of clubs`() {
                assertEquals(Card(Suit.CLUB, Weight.TWO), cardTransformer.convertToCard(3))
            }

            @Test
            fun `when number is 4 should return two of hearts`() {
                assertEquals(Card(Suit.HEART, Weight.TWO), cardTransformer.convertToCard(4))
            }

            @Test
            fun `when number is 5 should return three of diamonds`() {
                assertEquals(Card(Suit.DIAMOND, Weight.THREE), cardTransformer.convertToCard(5))
            }

            @Test
            fun `when number is 6 should return three of spades`() {
                assertEquals(Card(Suit.SPADE, Weight.THREE), cardTransformer.convertToCard(6))
            }

            @Test
            fun `when number is 24 should return eight of hearts`() {
                assertEquals(Card(Suit.HEART, Weight.SEVEN), cardTransformer.convertToCard(24))
            }

            @Test
            fun `when number is 50 should return ace of spades`() {
                assertEquals(Card(Suit.SPADE, Weight.ACE), cardTransformer.convertToCard(50))
            }
        }

        @Nested
        inner class `when request perameter is an invalid number` {
            @Test
            fun `should throw no such element exception`() {
                assertThrows<NoSuchElementException> { cardTransformer.convertToCard(60) }
            }
        }
    }
}
