package poker.model


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.beans.factory.annotation.Value
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@SpringBootApplication
@EnableConfigurationProperties(MatchConfiguration::class)
class PokerModelApplication {
    @Bean
    fun api(@Value("\${swagger.prefix}") prefix: String): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths { !it.startsWith("/error") }
        .build()
        .pathMapping(prefix)
}

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<PokerModelApplication>(*args)
}
