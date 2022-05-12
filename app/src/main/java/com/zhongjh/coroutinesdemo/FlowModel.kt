package com.zhongjh.coroutinesdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *
 * @author zhongjh
 * @date 2022/5/12
 */
class FlowModel : ViewModel() {

    private val _errorCode = MutableStateFlow("")
    val errorCode: StateFlow<String> = _errorCode

    fun fetchData() {
        viewModelScope.launch {
            val value = RetrofitClient.get().create(BannerApi::class.java).json()
            _errorCode.emit(value.errorCode.toString())
        }
    }

}