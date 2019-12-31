package com.smartappsolutions.turnera.network


import com.smartappsolutions.turnera.model.classes.LoginResponseTest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Response<LoginResponseTest>

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