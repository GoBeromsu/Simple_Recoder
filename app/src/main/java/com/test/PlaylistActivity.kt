package com.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.playlist.*

// Play list 보여주는 activity
class PlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist)

        button_back.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }


    // 클릭 시 해당하는 곡 삭제하는 함수
    fun deleteSong() {}

    // Download 아래에 있는 mp3 리스트트
    fun showSong() {
    }


}