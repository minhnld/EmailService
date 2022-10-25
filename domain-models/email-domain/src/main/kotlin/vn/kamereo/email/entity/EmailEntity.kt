package vn.kamereo.email.entity

enum class ContentType(val value: String) {
    PLAIN_TEXT("text/plain"),
    HTML("text/html")
}

data class EmailContact(val name: String, val address: String)

data class EmailBody(val type: ContentType, val value: String)

data class EmailInfo(
    val sender: EmailContact,
    val recipients: List<EmailContact>,
    val cc: List<EmailContact>,
    val bcc: List<EmailContact>,
    val subject: String,
    val body: EmailBody
)
