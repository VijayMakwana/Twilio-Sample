package com.twiliosample.model

import com.google.gson.annotations.SerializedName


data class MessageListResponse(
    @SerializedName("end")
    val end: Int?, // 0
    @SerializedName("first_page_uri")
    val firstPageUri: String?, // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages.json?PageSize=50&Page=0
    @SerializedName("messages")
    val messages: List<Message>,
    @SerializedName("next_page_uri")
    val nextPageUri: String?,
    @SerializedName("page")
    val page: Int?, // 0
    @SerializedName("page_size")
    val pageSize: Int?, // 50
    @SerializedName("previous_page_uri")
    val previousPageUri: String?,
    @SerializedName("start")
    val start: Int?, // 0
    @SerializedName("uri")
    val uri: String? // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages.json?PageSize=50&Page=0
) {
    data class Message(
        @SerializedName("account_sid")
        val accountSid: String?, // AC9f2a2b5a3bdd6581dda80ab310d363a2
        @SerializedName("api_version")
        val apiVersion: String?, // 2010-04-01
        @SerializedName("body")
        val body: String?, // Sent from your Twilio trial account - Twilio Test Message
        @SerializedName("date_created")
        val dateCreated: String?, // Sun, 20 Oct 2019 09:09:31 +0000
        @SerializedName("date_sent")
        val dateSent: String?, // Sun, 20 Oct 2019 09:09:31 +0000
        @SerializedName("date_updated")
        val dateUpdated: String?, // Sun, 20 Oct 2019 09:09:39 +0000
        @SerializedName("direction")
        val direction: String?, // outbound-api
        @SerializedName("error_code")
        val errorCode: String?,
        @SerializedName("error_message")
        val errorMessage: String?,
        @SerializedName("from")
        val from: String?, // +19412258517
        @SerializedName("messaging_service_sid")
        val messagingServiceSid: String?,
        @SerializedName("num_media")
        val numMedia: String?, // 0
        @SerializedName("num_segments")
        val numSegments: String?, // 1
        @SerializedName("price")
        val price: String?, // -0.01000
        @SerializedName("price_unit")
        val priceUnit: String?, // USD
        @SerializedName("sid")
        val sid: String?, // SM89d89afc412541198d01767f844e4c9c
        @SerializedName("status")
        val status: String?, // delivered
        @SerializedName("subresource_uris")
        val subresourceUris: SubresourceUris?,
        @SerializedName("to")
        val to: String?, // +917600847698
        @SerializedName("uri")
        val uri: String? // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages/SM89d89afc412541198d01767f844e4c9c.json
    ) {
        data class SubresourceUris(
            @SerializedName("feedback")
            val feedback: String?, // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages/SM89d89afc412541198d01767f844e4c9c/Feedback.json
            @SerializedName("media")
            val media: String? // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages/SM89d89afc412541198d01767f844e4c9c/Media.json
        )
    }
}