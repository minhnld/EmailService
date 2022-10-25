package vn.kamereo.email.grpc.service

import org.springframework.stereotype.Service
import vn.kamereo.email.EmailServiceGrpcKt
import vn.kamereo.email.EmailServiceOuterClass
import vn.kamereo.email.features.send_email.command.SendEmail
import vn.kamereo.email.grpc.proto.toEntity
import vn.kamereo.kommandbus.CommandBus

@Service
class EmailService(
    private val commandBus: CommandBus
) : EmailServiceGrpcKt.EmailServiceCoroutineImplBase() {

    override suspend fun sendEmail(
        request: EmailServiceOuterClass.SendEmailRequest
    ): EmailServiceOuterClass.SendEmailResponse {
        val sendEmailsCommand = request.emailInfosList.map { commandBus.execute(SendEmail(it.toEntity())) }
        return EmailServiceOuterClass
            .SendEmailResponse.newBuilder()
            .setStatus(sendEmailsCommand.map { it.statusCode }.joinToString()).build()
    }
}
