package com.twiliosample.network

import com.twiliosample.model.MessageListResponse
import com.twiliosample.model.SendMessageResponse
import com.twiliosample.util.Constants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    // send message API
    @FormUrlEncoded
    @POST("Accounts/${Constants.ACCOUNT_SID}/Messages.json")
    fun sendMessage(@Field("From") from: String, @Field("To") to: String, @Field("Body") body: String): Call<SendMessageResponse>

    // get messages API
    @GET("Accounts/${Constants.ACCOUNT_SID}/Messages.json")
    fun getMessages(): Call<MessageListResponse>

}
