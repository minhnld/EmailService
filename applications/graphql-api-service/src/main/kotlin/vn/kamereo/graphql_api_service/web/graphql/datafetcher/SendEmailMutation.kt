package vn.kamereo.graphql_api_service.web.graphql.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import vn.kamereo.email.entity.ContentType
import vn.kamereo.email.entity.EmailBody
import vn.kamereo.email.entity.EmailContact
import vn.kamereo.email.entity.EmailInfo
import vn.kamereo.email.features.send_email.command.SendEmail
import vn.kamereo.graphql_api_service.web.graphql.mapping.toGraphQL
import vn.kamereo.graphql_api_service.web.graphql.types.SendEmailResponse
import vn.kamereo.kommandbus.CommandBus

@DgsComponent
class SendEmailMutation(
    private val commandBus: CommandBus
) {
    @DgsMutation
    suspend fun sendEmail(
        @InputArgument input: vn.kamereo.graphql_api_service.web.graphql.types.SendEmailInput,
    ): SendEmailResponse {
        return commandBus.execute(
            SendEmail(
                EmailInfo(
                    EmailContact(input.sender.name, input.sender.address),
                    input.recipients.map { EmailContact(it.name, it.address) },
                    input.cc.map { EmailContact(it.name, it.address) },
                    input.bcc.map { EmailContact(it.name, it.address) },
                    input.subject,
                    EmailBody(ContentType.valueOf(input.body.type.name), input.body.value)
                )
            )
        ).toGraphQL()
    }
}
