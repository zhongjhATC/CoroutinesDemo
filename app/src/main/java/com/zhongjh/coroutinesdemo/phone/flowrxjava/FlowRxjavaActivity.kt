package com.zhongjh.coroutinesdemo.phone.flowrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.WorkerThread
import androidx.lifecycle.lifecycleScope
import com.zhongjh.coroutinesdemo.R
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import com.zhongjh.coroutinesdemo.phone.flow2.FlowModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 这是协程搭配Retrofit演示的Demo
 */
class FlowRxjavaActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val mViewModel: FlowRxjavaModel by viewModels()
    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tvContent = findViewById(R.id.tvContent)
        fetchData()
    }


    private fun fetchData() {
        lifecycleScope.launch {
            mViewModel.fetchData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}