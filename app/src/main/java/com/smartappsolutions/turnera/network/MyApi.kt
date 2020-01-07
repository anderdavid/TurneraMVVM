package com.smartappsolutions.turnera.network


import com.smartappsolutions.turnera.model.classes.LoginResponseTest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("autenticacion")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Response<ResponseBody>

    companion object{

        val testUrl ="https://api.simplifiedcoding.in/course-apis/mvvm/"
        val baseUrl="http://192.168.1.142/api/"

        operator fun invoke():MyApi{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}