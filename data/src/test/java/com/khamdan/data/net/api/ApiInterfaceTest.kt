package com.khamdan.data.net.api

import android.content.Context
import com.google.gson.Gson
import com.khamdan.data.net.OkHttpClientFactoryTest
import com.khamdan.data.net.RetrofitFactory
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiInterfaceTest {

    private val context = mock<Context>()
    private val retrofit = RetrofitFactory.getRetrofit(context, Gson(), OkHttpClientFactoryTest())

    private val service: ApiInterface
        get() = retrofit.create(ApiInterface::class.java)

    @Test
    fun getListPostWebservice() {
        service.getListPost().test()
            .assertNoErrors()
    }

    @Test
    fun getPostWebservice() {
        service.getPost(1).test()
            .assertNoErrors()
    }
}
