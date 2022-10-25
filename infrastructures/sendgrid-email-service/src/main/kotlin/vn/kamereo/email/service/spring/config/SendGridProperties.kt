package vn.kamereo.email.service.spring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

internal const val SEND_GRID_PREFIX = "sendgrid"

@ConstructorBinding
@ConfigurationProperties(prefix = SEND_GRID_PREFIX)
data class SendGridProperties(val apikey: String)
