package poker.model.converter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import poker.model.model.HandScore
import poker.model.model.HandType
import poker.model.model.MatchOutcome
import poker.model.model.MatchRecord

class MatchRecordConverterTest {
    val matchRecordConverter = MatchRecordConverter()

    @Nested
    inner class convertToMatchRecord {
        @Nested
        inner class `where player hand score is better than opponents hand score` {
            @Test
            fun `should return a MatchRecord with isHandWinning as true`() {
                val playerHandScore = HandScore(value = 403_021, type = HandType.TRIPLE)
                val opponentHandScore = HandScore(value = 300_249, type = HandType.TWO_PAIR)
                val matchOutcome = MatchOutcome(playerHandScore, opponentHandScore)

                val actual = matchRecordConverter.convertToMatchRecord(matchOutcome)
                val expected = MatchRecord(playerHandScore.type, playerHandScore.value, playerHandScore.type, true)

                assertEquals(expected, actual)
            }
        }

        @Nested
        inner class `where player hand score is worse than opponents hand score` {
            @Test
            fun `should return a MatchRecord with isHandWinning as false`() {
                val playerHandScore = HandScore(value = 403_021, type = HandType.TRIPLE)
                val opponentHandScore = HandScore(value = 500_242, type = HandType.STRAIGHT)

                val matchOutcome = MatchOutcome(playerHandScore, opponentHandScore)

                val actual = matchRecordConverter.convertToMatchRecord(matchOutcome)
                val expected = MatchRecord(playerHandScore.type, opponentHandScore.value, opponentHandScore.type, false)

                assertEquals(expected, actual)
            }
        }
    }
}
