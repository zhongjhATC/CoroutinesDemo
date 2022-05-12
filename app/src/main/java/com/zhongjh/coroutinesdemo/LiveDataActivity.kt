package com.zhongjh.coroutinesdemo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zhongjh.coroutinesdemo.databinding.ActivityLiveDataBinding

/**
 * 这是协程搭配Retrofit演示的Demo
 */
class LiveDataActivity : AppCompatActivity() {

    private val mViewModel: LiveDataModel by viewModels()

    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBind: ActivityLiveDataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_live_data
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