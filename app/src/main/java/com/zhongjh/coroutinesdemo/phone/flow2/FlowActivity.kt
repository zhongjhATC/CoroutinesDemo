package com.zhongjh.coroutinesdemo.phone.flow2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.zhongjh.coroutinesdemo.R
import com.zhongjh.coroutinesdemo.databinding.ActivityFlow2Binding
import kotlinx.coroutines.launch

/**
 * 这是协程搭配Retrofit演示的Demo
 * 不转换LiveData，通过代码形式操作UI
 */
class FlowActivity : AppCompatActivity() {

    private val mViewModel: FlowModel by viewModels()

    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBind: ActivityFlow2Binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_flow2
        )
        dataBind.lifecycleOwner = this
        dataBind.vm = mViewModel

        tvContent = findViewById(R.id.tvContent)
        fetchData()

        lifecycleScope.launch {
            mViewModel.errorCode.collect {
                tvContent.text = it
            }
        }
    }

    private fun fetchData() {
        mViewModel.fetchData()
    }
}