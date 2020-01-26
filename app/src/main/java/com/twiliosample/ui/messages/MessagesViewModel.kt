package com.twiliosample.ui.messages

import androidx.lifecycle.ViewModel
import com.twiliosample.data.AppRepository

class MessagesViewModel : ViewModel() {

    fun getMessagesList() = AppRepository.getMessages()

}