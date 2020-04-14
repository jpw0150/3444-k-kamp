package com.example.myapplication.Api

import com.example.myapplication.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("createingredient")
    fun createIngredient(
        @Field("food") food:String,
        @Field("amount") amount:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @PUT("trashfood/{id}")
    fun trashIngredient(
        @Field("id") id:Int
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @PUT("updateingredient/{id}")
    fun updateIngredient(
        @Field("id") id:Int
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @GET("allingredients")
    fun allIngredients(
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("createemployee")
    fun createEmployee(
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("position")position:String,
        @Field("wage")wage:Double
    ): Call<DefaultResponse>
}