package com.atu.measuretext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val arrays = arrayOf("abab","agag","jpag","aaaa","bbbb","cjcf")
    val random = Random(arrays.size)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_flush.setOnClickListener {
            sportview.setText(arrays[random.nextInt(6)])
        }
    }
}