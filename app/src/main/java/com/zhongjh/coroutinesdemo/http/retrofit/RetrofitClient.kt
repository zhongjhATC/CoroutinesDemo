package com.zhongjh.coroutinesdemo.http.retrofit

import com.zhongjh.coroutinesdemo.http.retrofit.HttpsUtils.sslSocketFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @author zhongjh
 * @date 2022/3/30
 *
 * 实现网络请求
 *
 * @param url     服务端根路径
 */
class RetrofitClient private constructor(
    url: String = "https://www.wanandroid.com/"
) {
    /**
     * retrofit本身
     */
    private val retrofit: Retrofit

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the `service` interface.
     */
    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit.create(service)
    }

    companion object {
        /**
         * 超时时间
         */
        private const val DEFAULT_TIMEOUT = 20

        private var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }

        fun get(): RetrofitClient {
            return instance!!
        }
    }


    init {
        val okHttpClientBuild: OkHttpClient.Builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为15s
            .connectionPool(
                ConnectionPool(
                    8,
                    15,
                    TimeUnit.SECONDS
                )
            )
        val sslParams = sslSocketFactory
        if (sslParams.trustManager != null) {
            okHttpClientBuild.sslSocketFactory(sslParams.sslSocketFactory, sslParams.trustManager!!)
        }
        retrofit = Retrofit.Builder()
            .client(okHttpClientBuild.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
    }
}