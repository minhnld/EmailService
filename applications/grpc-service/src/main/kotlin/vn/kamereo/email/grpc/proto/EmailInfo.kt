package vn.kamereo.email.grpc.proto

import vn.kamereo.email.Email
import vn.kamereo.email.entity.ContentType
import vn.kamereo.email.entity.EmailBody
import vn.kamereo.email.entity.EmailContact
import vn.kamereo.email.entity.EmailInfo

fun Email.EmailInfo.toEntity() = EmailInfo(
    this.sender.toEntity(),
    this.recipientsList.map { it.toEntity() },
    this.ccList.map { it.toEntity() },
    this.bccList.map { it.toEntity() },
    this.subject,
    this.body.toEntity()
)

fun Email.EmailInfo.EmailContact.toEntity() = EmailContact(this.name, this.address)

fun Email.EmailInfo.EmailBody.toEntity() = EmailBody(ContentType.valueOf(this.type), this.value)
