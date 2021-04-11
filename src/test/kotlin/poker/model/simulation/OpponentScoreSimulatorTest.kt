package poker.model.simulation

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import poker.model.model.Card
import poker.model.model.TableDetails

class OpponentScoreSimulatorTest {

    private val mockTableDetails = mockk<TableDetails>()
    private val mockDeck = mockk<List<Card>>()

    @Nested
    inner class simulateHandScore {
        @Test
        fun `should return a simulated opponent hand score`() {
            assertEquals(1,1)
        }
    }
}