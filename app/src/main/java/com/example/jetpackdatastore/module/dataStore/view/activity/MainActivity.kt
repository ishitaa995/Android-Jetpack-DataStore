package com.example.jetpackdatastore.module.dataStore.view.activity

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.jetpackdatastore.R
import com.example.jetpackdatastore.dataStore.UserPreferences
import kotlinx.coroutines.launch

class MainActivity :AppCompatActivity() {

    private lateinit var etDataToStore : EditText
    private lateinit var  btnSave: Button
    private lateinit var tvShowStoredData : TextView
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initViews()
        userPreferences = UserPreferences(this)
        atoreDataInDataStore()
        showStoredData()
    }

   fun getLayout() :Int{
       return R.layout.activity_main
   }

   fun  initViews() {
    etDataToStore = findViewById(R.id.et_data)
    btnSave = findViewById(R.id.btn_save)
       tvShowStoredData = findViewById(R.id.tv_show_stored_data)
   }

   fun atoreDataInDataStore(){
       btnSave.setOnClickListener {
           val bookmark = etDataToStore.text.toString().trim()
           lifecycleScope.launch {
               userPreferences.saveBookmark(bookmark)
           }
       }
   }

    fun showStoredData(){
        userPreferences.bookmark.asLiveData().observe(this, Observer {
            tvShowStoredData.text = it
            })
    }
}