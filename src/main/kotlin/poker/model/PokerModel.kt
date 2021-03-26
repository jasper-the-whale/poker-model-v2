package poker.model

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MatchConfiguration::class)
class PokerModelApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<PokerModelApplication>(*args)
}
