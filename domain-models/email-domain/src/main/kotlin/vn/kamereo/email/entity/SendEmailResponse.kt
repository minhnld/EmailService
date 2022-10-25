package vn.kamereo.email.entity

data class SendEmailResponse(
    val statusCode: Int,
    val body: String,
    val header: Map<String, String>
)
