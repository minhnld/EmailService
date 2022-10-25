package vn.kamereo.email.service.service

import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import com.sendgrid.helpers.mail.objects.Personalization
import mu.KotlinLogging
import org.springframework.stereotype.Component
import vn.kamereo.email.entity.EmailInfo
import vn.kamereo.email.entity.EmailService
import vn.kamereo.email.entity.SendEmailResponse

private val logger = KotlinLogging.logger {}

@Component
open class SendgridEmailService(
    private val sendGridClient: SendGrid,
) : EmailService {

    override fun sendEmail(emailInfo: EmailInfo): SendEmailResponse {
        val sendResponse = sendGridClient.api(requestBuilder(emailInfo))
        return SendEmailResponse(
            sendResponse.statusCode,
            sendResponse.body,
            sendResponse.headers
        )
    }

    private fun requestBuilder(emailInfo: EmailInfo): Request {
        val request = Request()
        val mail = Mail()
        mail.setFrom(Email(emailInfo.sender.address, emailInfo.sender.name))
        mail.addContent(Content(emailInfo.body.type.value, emailInfo.body.value))
        mail.setSubject(emailInfo.subject)

        val personalization = Personalization()
        for (recipient in emailInfo.recipients) {
            personalization.addTo(Email(recipient.address, recipient.name))
        }
        if (emailInfo.cc.isNotEmpty()) {
            emailInfo.cc.map { ccUser ->
                personalization.addCc(Email(ccUser.address, ccUser.name))
            }
        }
        if (emailInfo.bcc.isNotEmpty()) {
            emailInfo.bcc.map { ccUser ->
                personalization.addCc(Email(ccUser.address, ccUser.name))
            }
        }
        mail.addPersonalization(personalization)
        request.method = Method.POST
        request.endpoint = "mail/send"
        request.body = mail.build()
        return request
    }
}
