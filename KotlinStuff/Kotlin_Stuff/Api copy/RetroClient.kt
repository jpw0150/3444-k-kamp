package com.example.myapplication.Api

import android.util.Base64
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {


    private const val BASE_URL = "http://10.0.2.2:8080/MyApp/public/"
    private val AUTH = "Basic"+ Base64.encodeToString("qucewayne:678999".toByteArray(), Base64.NO_WRAP)

    private val okHttpClient = OkHttpClient.Builder()
      .addInterceptor { chain ->
          val original = chain.request()

          val requestBuilder = original.newBuilder()
              .addHeader("Authorization" ,
                  AUTH
              )
              .method(original.method(), original.body())

          val request = requestBuilder.build()
          chain.proceed(request)
      }.build()

   val gson = GsonBuilder()
        .setLenient()
        .create()

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }

}