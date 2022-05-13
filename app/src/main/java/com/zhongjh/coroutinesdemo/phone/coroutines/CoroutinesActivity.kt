package com.zhongjh.coroutinesdemo.phone.coroutines

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zhongjh.coroutinesdemo.R
import kotlinx.coroutines.*

/**
 * 单单演示协程
 * @author zhongjh
 * @date 2022/5/11
 */
class CoroutinesActivity : AppCompatActivity() {

    private val scope = MainScope()
    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        tvContent = findViewById(R.id.tvContent)
        fetchData()
    }

    private fun fetchData() {
        scope.launch {
            val deferred = async(Dispatchers.IO) {
                // network request
                delay(3000)
                "deferred"
            }
            tvContent.text = deferred.await()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}