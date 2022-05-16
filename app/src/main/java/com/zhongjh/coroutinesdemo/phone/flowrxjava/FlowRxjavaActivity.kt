package com.zhongjh.coroutinesdemo.phone.flowrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.WorkerThread
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.zhongjh.coroutinesdemo.R
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import com.zhongjh.coroutinesdemo.phone.flow2.FlowModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * 这是协程搭配Retrofit演示的Demo
 */
class FlowRxjavaActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val mViewModel: FlowRxjavaModel by viewModels()
    lateinit var tvContent: TextView
    lateinit var btnTest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tvContent = findViewById(R.id.tvContent)
        btnTest = findViewById(R.id.btnTest)

        btnTest.setOnClickListener {
            lifecycleScope.launch {
                whenStarted {
                    mViewModel.errorCode.collect {
                        tvContent.text = it
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}