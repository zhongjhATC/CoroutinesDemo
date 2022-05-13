package com.zhongjh.coroutinesdemo.phone.flow

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zhongjh.coroutinesdemo.R
import com.zhongjh.coroutinesdemo.databinding.ActivityFlowBinding

/**
 * 这是协程搭配Retrofit演示的Demo
 */
class FlowActivity : AppCompatActivity() {

    private val mViewModel: FlowModel by viewModels()

    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBind: ActivityFlowBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_flow
        )
        dataBind.lifecycleOwner = this
        dataBind.vm = mViewModel

        tvContent = findViewById(R.id.tvContent)
        fetchData()
    }

    private fun fetchData() {
        mViewModel.fetchData()
    }
}