package com.zhongjh.coroutinesdemo.phone.flowrxjava

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 *
 * @author zhongjh
 * @date 2022/5/13
 */
class FlowRxjavaModel : ViewModel() {

    val errorCode = MutableStateFlow("")

    fun fetchData() {
//        viewModelScope.launch {
//            val value = fetchData(
//                onStart = { Log.i("FlowRxjavaModel", "onStart") },
//                onComplete = { Log.i("FlowRxjavaModel", "onComplete") },
//                onError = { Log.i("FlowRxjavaModel", "onError") }
//            )
//            value.asLiveData
//            errorCode.emit()
//        }
    }

    private val _weatherForecast = fetchData(
        onStart = { Log.i("FlowRxjavaModel", "onStart") },
        onComplete = { Log.i("FlowRxjavaModel", "onComplete") },
        onError = { Log.i("FlowRxjavaModel", "onError") }
    )
        .asLiveData(viewModelScope.coroutineContext)

    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val value = RetrofitClient.get().create(BannerApi::class.java).json()
        emit(value.errorCode.toString())
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.flowOn(Dispatchers.IO)

}