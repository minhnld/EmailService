package vn.kamereo.email.entity

interface EmailService {
    /**
     * Send email
     * @param
     * @return Unit.
     * @author Minh Nguyen
     */
    fun sendEmail(emailInfo: EmailInfo): SendEmailResponse
}
