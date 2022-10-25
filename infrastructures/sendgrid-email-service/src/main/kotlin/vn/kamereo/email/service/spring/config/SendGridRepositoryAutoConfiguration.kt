package vn.kamereo.email.service.spring.config

import com.sendgrid.SendGrid
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import vn.kamereo.email.entity.EmailService
import vn.kamereo.email.service.service.SendgridEmailService

@Configuration
@ConditionalOnProperty(
    prefix = SEND_GRID_PREFIX,
    name = [
        "apikey"
    ]
)
@ConditionalOnMissingBean(
    EmailService::class,
)
@Import(SendGridClientConfig::class)
@EnableConfigurationProperties(SendGridProperties::class)
open class SendGridRepositoryAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(EmailService::class)
    open fun emailServiceRepository(
        sendGridClient: SendGrid,
    ): EmailService = SendgridEmailService(
        sendGridClient
    )
}
