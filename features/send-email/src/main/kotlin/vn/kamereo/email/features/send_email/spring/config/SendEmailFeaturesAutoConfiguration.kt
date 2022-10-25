package vn.kamereo.email.features.send_email.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vn.kamereo.email.entity.EmailService
import vn.kamereo.email.features.send_email.command.SendEmailHandler

@Configuration
open class SendEmailFeaturesAutoConfiguration {

    @Bean
    open fun sendEmailHandlerHandler(
        emailService: EmailService,
    ) = SendEmailHandler(
        emailService
    )
}
