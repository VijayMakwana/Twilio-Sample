package com.twiliosample.network

import com.twiliosample.BuildConfig
import com.twiliosample.util.Constants
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val TWILIO_BASE_URL: String = if (BuildConfig.DEBUG) {
        "https://api.twilio.com/2010-04-01/"
    } else {
        "https://api.twilio.com/2010-04-01/"
    }


    val twilioService: ApiInterface by lazy {
        val builder = Retrofit.Builder()
            .baseUrl(TWILIO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val client = OkHttpClient.Builder()
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(loggingInterceptor)
        }

        // add basic auth in client
        val authToken = Credentials.basic(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN)
        val authenticationInterceptor = AuthenticationInterceptor(authToken)
        client.addInterceptor(authenticationInterceptor)

        builder.client(client.build())
        val retrofit = builder.build()
        retrofit.create(ApiInterface::class.java)
    }

    class AuthenticationInterceptor(private val authToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            val builder = original.newBuilder()
                .header("Authorization", authToken)

            val request = builder.build()
            return chain.proceed(request)
        }
    }
}