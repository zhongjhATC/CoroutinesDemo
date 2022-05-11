package com.zhongjh.coroutinesdemo.http

import com.zhongjh.coroutinesdemo.bean.ApiEntity
import com.zhongjh.coroutinesdemo.bean.Banner
import com.zhongjh.coroutinesdemo.bean.ProjectContent
import com.zhongjh.coroutinesdemo.bean.ProjectTree
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BannerApi {

    @GET("banner/json")
    suspend fun json(): ApiEntity<List<Banner>>

}