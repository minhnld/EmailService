package vn.kamereo.graphql_api_service.web.graphql.mapping

import vn.kamereo.email.entity.SendEmailResponse
import vn.kamereo.graphql_api_service.web.graphql.types.ResponseHeader
import vn.kamereo.graphql_api_service.web.graphql.types.SendEmailResponse as SendEmailResponseGraphQL

fun SendEmailResponse.toGraphQL(): SendEmailResponseGraphQL = SendEmailResponseGraphQL
    .newBuilder()
    .statusCode(this.statusCode)
    .body(this.body)
    .header(
        this.header.entries.map {
            ResponseHeader(it.key, it.value)
        }
    )
    .build()
