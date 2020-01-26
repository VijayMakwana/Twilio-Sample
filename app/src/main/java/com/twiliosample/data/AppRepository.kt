package com.twiliosample.data

import androidx.lifecycle.LiveData
import com.twiliosample.model.MessageListResponse
import com.twiliosample.model.SendMessageResponse
import com.twiliosample.network.ApiClient
import com.twiliosample.util.Resource
import getResult

object AppRepository {

    fun sendMessage(
        from: String,
        to: String,
        message: String
    ): LiveData<Resource<SendMessageResponse>> =
        ApiClient.twilioService.sendMessage(from, to, message).getResult()

    fun getMessages(): LiveData<Resource<MessageListResponse>> =
        ApiClient.twilioService.getMessages().getResult()
}