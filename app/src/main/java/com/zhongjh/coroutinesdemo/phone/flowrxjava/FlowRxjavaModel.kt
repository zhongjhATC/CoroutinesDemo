package com.zhongjh.coroutinesdemo.phone.flowrxjava

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 *
 * @author zhongjh
 * @date 2022/5/13
 */
class FlowRxjavaModel : ViewModel() {

    val errorCode = fetchData(
        onStart = { Log.i("FlowRxjavaModel", "onStart") },
        onComplete = { Log.i("FlowRxjavaModel", "onComplete") },
        onError = { Log.i("FlowRxjavaModel", "onError") }
    )

    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<String> = flow {
        val value = RetrofitClient.get().create(BannerApi::class.java).json()
        emit(value.errorCode.toString())
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.catch {
    }.flowOn(Dispatchers.IO)

}