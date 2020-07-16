package com.test

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

import java.io.IOException
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // 녹음 시작
        // 퍼미션 추가(외부 저장소 저장 해야됨
        button_start.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //Permission is not granted

                ActivityCompat.requestPermissions(this, permissions, 0)
            } else {
                startRecording()
            }
        }
        // 녹음 중지
        button_stop.setOnClickListener {
            stopRecording()
        }
        // playlistActivity로 넘어가는 버튼
        button_list.setOnClickListener {
            var intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
        }

    }

    private fun startRecording() {
        //config and create MediaRecorder Object
        val timeFormat = SimpleDateFormat("yyyyMMddHHmm")
        val time = timeFormat.format(java.util.Date())
        val fileName: String = time + ".mp3"

        output =
            Environment.getExternalStorageDirectory().absolutePath + "/Download/" + fileName //내장메모리 밑에 위치
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource((MediaRecorder.AudioSource.MIC))
        mediaRecorder?.setOutputFormat((MediaRecorder.OutputFormat.MPEG_4))
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)

        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            Toast.makeText(this, "레코딩 시작되었습니다.", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun stopRecording() {
        if (state) {
            mediaRecorder?.stop()
            mediaRecorder?.reset()
            mediaRecorder?.release()
            state = false
            Toast.makeText(this, "중지 되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "레코딩 상태가 아닙니다.", Toast.LENGTH_SHORT).show()
        }
    }

}