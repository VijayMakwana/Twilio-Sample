package com.twiliosample.ui.contacts.sendmessage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twiliosample.data.AppRepository
import com.twiliosample.model.SendMessageResponse
import com.twiliosample.util.Resource

class SendMessageViewModel : ViewModel() {

    fun sendMessage(
        from: String,
        to: String,
        message: String
    ): LiveData<Resource<SendMessageResponse>> = AppRepository.sendMessage(from, to, message)

}