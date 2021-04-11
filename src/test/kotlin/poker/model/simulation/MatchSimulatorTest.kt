package poker.model.simulation

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import poker.model.model.HandScore
import poker.model.model.HandType
import poker.model.model.MatchOutcome
import org.junit.jupiter.api.Assertions.assertEquals
import poker.model.model.Card
import poker.model.model.TableDetails

class MatchSimulatorTest {
    private val mockPlayerScoreSimulator = mockk<PlayerScoreSimulator>()
    private val mockOpponentScoreSimulator = mockk<OpponentScoreSimulator>()
    private val mockMatchSimulator = MatchSimulator(mockPlayerScoreSimulator, mockOpponentScoreSimulator)

    private val aPlayerHandScore = HandScore(value = 403_021, type = HandType.TRIPLE)
    private val anOpponentHandScore = HandScore(value = 403_021, type = HandType.TRIPLE)

    @Nested
    inner class something {
        @Test
        fun `should return a MatchOutcome`() {
            val mockTableDetails = mockk<TableDetails>()
            val mockDeck = mockk<List<Card>>()
            every { mockPlayerScoreSimulator.simulateHandScore(any(), any()) } returns aPlayerHandScore
            every { mockOpponentScoreSimulator.simulateHandScore(any(), any()) } returns anOpponentHandScore

            val actual = mockMatchSimulator.simulate(mockTableDetails, mockDeck)
            val expected = MatchOutcome(aPlayerHandScore, anOpponentHandScore)

            assertEquals(expected, actual)
        }
    }
}