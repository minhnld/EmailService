package vn.kamereo.email.grpc.spring.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GrpcProperties::class)
open class GrpcConfig(private val properties: GrpcProperties)
