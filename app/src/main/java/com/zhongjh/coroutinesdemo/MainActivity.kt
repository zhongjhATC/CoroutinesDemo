package com.zhongjh.coroutinesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/**
 * 这是搭配Retrofit演示的Demo
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCoroutines).setOnClickListener {
            val intent = Intent(this, CoroutinesActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnRetrofit2).setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnLiveData).setOnClickListener {
            val intent = Intent(this, LiveDataActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnFlow).setOnClickListener {
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
        }
    }

}