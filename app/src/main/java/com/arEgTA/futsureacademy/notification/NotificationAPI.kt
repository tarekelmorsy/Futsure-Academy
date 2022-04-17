package com.arEgTA.futsureacademy.notification

import com.arEgTA.futsureacademy.model.PushNotification
import com.arEgTA.futsureacademy.utils.Constants
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import okhttp3.ResponseBody
import retrofit2.Response


interface NotificationAPI {

    @Headers("Authorization: key=${Constants.SERVER_KEY}", "Content-Type:${Constants.CONTENT_TYPE}")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}