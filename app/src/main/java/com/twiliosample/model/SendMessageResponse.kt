package com.twiliosample.model

import com.google.gson.annotations.SerializedName


data class SendMessageResponse(
    @SerializedName("account_sid")
    val accountSid: String?, // AC9f2a2b5a3bdd6581dda80ab310d363a2
    @SerializedName("api_version")
    val apiVersion: String?, // 2010-04-01
    @SerializedName("body")
    val body: String?, // Sent from your Twilio trial account - Twilio Test Message
    @SerializedName("date_created")
    val dateCreated: String?, // Sun, 20 Oct 2019 09:09:31 +0000
    @SerializedName("date_sent")
    val dateSent: String?,
    @SerializedName("date_updated")
    val dateUpdated: String?, // Sun, 20 Oct 2019 09:09:31 +0000
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
    val price: String?,
    @SerializedName("price_unit")
    val priceUnit: String?, // USD
    @SerializedName("sid")
    val sid: String?, // SM89d89afc412541198d01767f844e4c9c
    @SerializedName("status")
    val status: String?, // queued
    @SerializedName("subresource_uris")
    val subresourceUris: SubresourceUris?,
    @SerializedName("to")
    val to: String?, // +917600847698
    @SerializedName("uri")
    val uri: String? // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages/SM89d89afc412541198d01767f844e4c9c.json
) {
    data class SubresourceUris(
        @SerializedName("media")
        val media: String? // /2010-04-01/Accounts/AC9f2a2b5a3bdd6581dda80ab310d363a2/Messages/SM89d89afc412541198d01767f844e4c9c/Media.json
    )
}