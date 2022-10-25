package vn.kamereo.email.grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import vn.kamereo.spring.config.FQCNBeanNameGenerator

@SpringBootApplication(
    exclude = [
        JooqAutoConfiguration::class,
        DataSourceAutoConfiguration::class,
        ElasticsearchRestClientAutoConfiguration::class
    ]
)
@ComponentScan(nameGenerator = FQCNBeanNameGenerator::class)
class Application

fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<Application>(*args)
}
