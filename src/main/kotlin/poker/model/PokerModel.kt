package poker.model

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import poker.model.simulation.MatchSummaryConverter
import poker.model.simulation.SimulationHandler
import poker.model.transformer.CardTransformer

@SpringBootApplication
@EnableConfigurationProperties(EventConfiguration::class)
class PokerModelApplication {
    @Bean
    fun cardTransformer() = CardTransformer()

    @Bean
    fun simulationHandler() = SimulationHandler()

    @Bean
    fun matchSummaryConverter() = MatchSummaryConverter()
}

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<PokerModelApplication>(*args)
}
