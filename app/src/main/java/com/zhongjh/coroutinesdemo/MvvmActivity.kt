package com.zhongjh.coroutinesdemo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zhongjh.coroutinesdemo.databinding.ActivityMvvmBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.cancel

/**
 * 这是协程搭配Retrofit演示的Demo
 */
class MvvmActivity : AppCompatActivity() {

    private val mViewModel: MvvmModel by viewModels()

    lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBind: ActivityMvvmBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_mvvm
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