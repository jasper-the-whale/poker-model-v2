package poker.model.simulation

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class MatchSimulatorTest {
    @Nested
    inner class simulateMatch {
        @Nested
        inner class `when game state is pre flog` {
            @Test
            fun `should return a simulated match`() {

            }
        }

        @Nested
        inner class `when game state is after the flog` {
            @Test
            fun `should return a simulated match`() {

            }
        }

        @Nested
        inner class `when game state is after the turn` {
            @Test
            fun `should return a simulated match`() {

            }
        }

        @Nested
        inner class `when game state is after the river` {
            @Test
            fun `should return a simulated match`() {

            }
        }
    }

    @Nested
    inner class isMyHandBest {
        @Nested
        inner class `when you have the best hand` {
            @Test
            fun `should return true`() {

            }
        }

        @Nested
        inner class `when your opponent has the best hand` {
            @Test
            fun `should return false`() {

            }
        }
    }

    @Nested
    inner class getBestHandScore {
        @Nested
        inner class `when you have the best hand` {
            @Test
            fun `should return your hand score`() {

            }
        }

        @Nested
        inner class `when your opponent has the best hand` {
            @Test
            fun `should return your opponent's hand score`() {

            }
        }
    }

    @Nested
    inner class getBestHandType {
        @Nested
        inner class `when you have the best hand` {
            @Test
            fun `should return your hand hand type`() {

            }
        }

        @Nested
        inner class `when your opponent has the best hand` {
            @Test
            fun `should return your opponent's hand type`() {

            }
        }
    }
}