package com.zhongjh.coroutinesdemo.phone.flow2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 *
 * @author zhongjh
 * @date 2022/5/12
 */
class FlowModel : ViewModel() {

    val errorCode = MutableStateFlow("")

    fun fetchData() {
        viewModelScope.launch {
            val value = RetrofitClient.get().create(BannerApi::class.java).json()
            errorCode.emit(value.errorCode.toString())
        }
    }

}