package com.smartappsolutions.turnera.network


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface  ApiRest{

    @FormUrlEncoded
    @POST("autenticacion")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Response<ResponseBody>

    companion object{

        operator fun invoke(baseUrl:String):ApiRest{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRest::class.java)
        }
    }
}