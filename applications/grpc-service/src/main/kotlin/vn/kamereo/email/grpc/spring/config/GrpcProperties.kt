package vn.kamereo.email.grpc.spring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kamereo.grpc")
data class GrpcProperties(val server: ServerProperties) {

    data class ServerProperties(
        val port: Int
    )
}
