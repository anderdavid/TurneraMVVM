package com.smartappsolutions.turnera.network

import android.telecom.CallScreeningService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<ResponseBody>

    companion object{

        val testUrl ="https://api.simplifiedcoding.in/course-apis/mvvm/"

        operator fun invoke():MyApi{
            return Retrofit.Builder()
                .baseUrl(testUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}