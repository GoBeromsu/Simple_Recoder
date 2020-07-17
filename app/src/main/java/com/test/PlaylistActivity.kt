package com.test

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.playlist.*
import java.io.File

// Play list 보여주는 activity
class PlaylistActivity : AppCompatActivity() {

    var mp3Path: String = Environment.getExternalStorageDirectory().absolutePath + "/Download/"
    var listFiles = File(mp3Path).listFiles()
    val audioPlay = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist)

        var fileName: String? = null
        var extName: String? = null
        var mp3List = mutableListOf<String>()

        for (file in listFiles) {
            fileName = file.getName()
            extName = fileName.substring(fileName.length - 3)
            if (extName == "mp3") {
                println(fileName)
                mp3List.add(fileName)
            }
        }

        setContentView(R.layout.playlist)
        view_mp3.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mp3List)

        view_mp3.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val seletedVoice = parent.getItemAtPosition(position) as String
                Toast.makeText(
                    this,
                    "${Environment.getExternalStorageDirectory()}/Download/" + seletedVoice,
                    Toast.LENGTH_SHORT
                ).show()
                audioPlay.setDataSource("${Environment.getExternalStorageDirectory()}/Download/" + seletedVoice)
                audioPlay.prepare()
                audioPlay.start()

            }

        // 클릭한 객체? 아이템? 곡의 텍스트 정보를 가져와서 그 정보와 일치하는 mp3를 재생하면 되지!

        button_back.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}


