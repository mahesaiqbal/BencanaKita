package com.mahesaiqbal.bencanakita.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object InstanceRetrofit {

    fun create(): Service {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://sibenta-api.hessananda.com/")
            .build()
        return retrofit.create(Service::class.java)
    }
}