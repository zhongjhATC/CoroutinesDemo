package com.zhongjh.coroutinesdemo

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import kotlinx.coroutines.launch


/**
 *
 * @author zhongjh
 * @date 2022/5/12
 */
class MvvmModel : ViewModel() {

    val errorCode: MutableLiveData<String> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            val value = RetrofitClient.get().create(BannerApi::class.java).json()
            errorCode.postValue(value.errorCode.toString())
        }
    }

}