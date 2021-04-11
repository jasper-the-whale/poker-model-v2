package poker.model.controller

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import poker.model.model.InvalidTableSummary
import poker.model.model.MatchResults
import poker.model.model.MatchSummary
import poker.model.model.ValidTableSummary
import poker.model.simulation.MatchSummaryConverter
import poker.model.simulation.SimulationHandler
import poker.model.transformer.CardTransformer

class ControllerTest {
    private val mockCardTransformer = mockk<CardTransformer>()
    private val mockSimulationHandler = mockk<SimulationHandler>()
    private val mockMatchSummaryConverter = mockk<MatchSummaryConverter>()

    private val controller = Controller(mockCardTransformer, mockSimulationHandler, mockMatchSummaryConverter)

    private val myCards = listOf(1, 2)
    private val totalPlayers = 5
    private val tableCards = listOf<Int>()
    private val pot = 1000L
    private val betToLose = 250L

    @AfterEach
    fun clear() {
        clearAllMocks()
    }

    @Nested
    inner class `when toTableDetails returns a valid table summary` {
        @Test
        fun `should return a match summary`() {
            val mockTableSummary = ValidTableSummary(mockk())
            val mockMatchResult = mockk<MatchResults>()
            val mockMatchSummary = mockk<MatchSummary>()

            every { mockCardTransformer.toTableDetails(any(), any(), any(), any(), any()) } returns mockTableSummary
            every { mockSimulationHandler.getMatchResults(any()) } returns mockMatchResult
            every { mockMatchSummaryConverter.convertToMatchSummary(any()) } returns mockMatchSummary

            val actual = controller.getHandOutcomes(myCards, totalPlayers , tableCards , pot, betToLose)
            val expected = ResponseEntity.status(HttpStatus.OK).body(mockMatchSummary)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class `when toTableDetails returns an invalid table summary` {
        @Test
        fun `should return response with status bad request`() {
            every { mockCardTransformer.toTableDetails(any(), any(), any(), any(), any()) } returns InvalidTableSummary

            val actual = controller.getHandOutcomes(myCards, totalPlayers , tableCards , pot, betToLose)
            val expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)

            assertEquals(expected, actual)
        }
    }

}