package com.zhongjh.coroutinesdemo.phone.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.zhongjh.coroutinesdemo.R
import com.zhongjh.coroutinesdemo.http.BannerApi
import com.zhongjh.coroutinesdemo.http.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * 这是协程搭配Retrofit演示的Demo
 */
class RetrofitActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tvContent = findViewById(R.id.tvContent)
        fetchData()
    }

    private fun fetchData() {
        launch {
            val a = RetrofitClient.get().create(BannerApi::class.java).json()
            tvContent.text = a.errorCode.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}