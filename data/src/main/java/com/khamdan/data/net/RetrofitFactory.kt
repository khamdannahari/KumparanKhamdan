package com.khamdan.data.net

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission

import com.google.gson.Gson

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    // Base URL: always ends with /
    private const val URL_MAIN_WEBSERVICE = "https://jsonplaceholder.typicode.com/"

    /**
     * Get [Retrofit] instance.
     * @return instances of [Retrofit]
     */
    @RequiresPermission(value = Manifest.permission.INTERNET)
    fun getRetrofit(
        context: Context,
        gson: Gson,
        okHttpClientFactory: OkHttpClientFactory
    ): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL_MAIN_WEBSERVICE)
            .client(okHttpClientFactory.createOkHttpClient(context))
            .build()

}
