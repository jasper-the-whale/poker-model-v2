package poker.model.simulation

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import poker.model.domain.ApiResponse
import poker.model.domain.MatchResult
import java.io.File

internal class MatchSummaryTest {
    @Nested
    inner class `getMatchSummary` {
        @Test
        fun `should return a valid ApiResponse for a list of match results`() {
            val mapper = jacksonObjectMapper()
            val matchResults: List<MatchResult> = mapper.readValue(
                File("src/test/resources/MatchResultsExample.json").readText(Charsets.UTF_8)
            )
            val expectedResult: ApiResponse = mapper.readValue(
                File("src/test/resources/ApiResponseExample.json").readText(Charsets.UTF_8)
            )

            val actualResult = getMatchSummary(matchResults, 1000, 200)

            assertEquals(expectedResult, actualResult)
        }
    }
}
