package vn.kamereo.email.features.send_email.command

import co.elastic.apm.api.CaptureSpan
import vn.kamereo.email.entity.EmailInfo
import vn.kamereo.email.entity.EmailService
import vn.kamereo.email.entity.SendEmailResponse
import vn.kamereo.kommandbus.Command
import vn.kamereo.kommandbus.CommandHandler

data class SendEmail(val emailInfos: EmailInfo) : Command<SendEmailResponse>

class SendEmailHandler(
    private val emailService: EmailService
) : CommandHandler<SendEmail, SendEmailResponse> {
    @CaptureSpan(type = "commandbus", subtype = "handler")
    override suspend fun handle(command: SendEmail): SendEmailResponse {
        return emailService.sendEmail(command.emailInfos)
    }
}
