package vn.kamereo.email.grpc.server

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import vn.kamereo.email.grpc.service.EmailService
import vn.kamereo.email.grpc.spring.config.GrpcProperties
import vn.kamereo.grpc.server.Server

@Component
class ServerRunner(
    properties: GrpcProperties,
    emailService: EmailService
) : CommandLineRunner {
    private val server = Server.Builder()
        .setPort(properties.server.port)
        .addService(emailService)
        .build()

    override fun run(vararg args: String?) {
        server.listenAndServe()
    }
}
