package vn.kamereo.email.service.spring.config

import com.sendgrid.SendGrid
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SendGridProperties::class)
open class SendGridClientConfig(
    private val properties: SendGridProperties
) {

    @Bean
    open fun sendGridClient(): SendGrid = SendGrid(properties.apikey)
}
