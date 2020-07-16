package com.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.playlist.*
import android.widget.ListView
import java.io.File

// Play list 보여주는 activity
class PlaylistActivity : AppCompatActivity() {

    var mp3Path: String = Environment.getExternalStorageDirectory().absolutePath + "/Download/"
    var listFiles = File(mp3Path).listFiles()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist)

        var fileName: String? = null
        var extName: String? = null
        var mp3List = mutableListOf<String>()


        for (file in listFiles) {
            fileName = file.getName()
            extName = fileName.substring(fileName.length-3)
            if (extName == "mp3") {
                println(fileName)
                mp3List.add(fileName)
            }
        }



        button_back.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
//
//    private class mp3ListAdapter(context: Context) : BaseAdapter() {
//
//
//        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
//            TODO("Not yet implemented")
//        }
//
//        override fun getItem(p0: Int): Any {
//            TODO("Not yet implemented")
//        }
//
//        override fun getItemId(p0: Int): Long {
//            TODO("Not yet implemented")
//        }
//
//        override fun getCount(): Int {
//            TODO("Not yet implemented")
//        }
//
//
//    }
}


