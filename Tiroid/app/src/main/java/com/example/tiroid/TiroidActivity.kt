package com.example.tiroid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tiroid.databinding.ActivityTiroidBinding

class TiroidActivity : AppCompatActivity() {
    lateinit var user : User
    lateinit var binding : ActivityTiroidBinding
    lateinit var sp : SharedPreferences
    var mList : MutableList<Result> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiroidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNav = binding.bottomNav
        val navController = findNavController(R.id.fragment)

        bottomNav.setupWithNavController(navController)
        sp = applicationContext.getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE)

    }

    fun getList():MutableList<Result> {
        return mList
    }
}