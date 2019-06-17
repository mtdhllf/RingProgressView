package com.mtdhllf.widget.progress.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ringView.ringBgColor = Color.parseColor("#ff464647")
        ringView.duration = 5000L
        ringView.startProgress = 0f
        ringView.endProgress = 1000f
        ringView.progress = 700f
    }
}
