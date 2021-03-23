package poker.model.simulation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import poker.model.domain.Card
import poker.model.domain.Suit
import poker.model.domain.Weight

internal class SimulationHandlerTest {
    @Nested
    inner class calculateOutcomeProbabilities {

    }

    @Nested
    inner class translateToCard {
        @Nested
        inner class `when request perameter is a valid number` {
            @Test
            fun `should return ace of spades`() {
                val testParameter = 50
                assertEquals(Card(Suit.SPADE, Weight.ACE), testParameter.translateToCard())
            }

            @Test
            fun `should return eight of hearts`() {
                val testParameter = 24
                assertEquals(Card(Suit.HEART, Weight.EIGHT), testParameter.translateToCard())
            }
        }

        @Nested
        inner class `when request perameter is an invalid number` {
            @Test
            fun `should throw exception`() {
                val testParameter = 60
                assertThrows<NoSuchElementException> { testParameter.translateToCard() }
            }
        }
    }
}
