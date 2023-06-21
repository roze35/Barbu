package com.example.barbu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.barbu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingMain : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        Referee.addPlayer(GraphicalPlayer("Lolo"))
        Referee.addPlayer(Player("ouest"))
        Referee.addPlayer(Player("north"))
        Referee.addPlayer(Player("east"))


        Log.d("affichage","onCreate: MainActivity")
        bindingMain= DataBindingUtil.setContentView(this,R.layout.activity_main)
        //bindingMain.btPlay.setOnClickListener({
        //    Log.d("affichage","youpee ca marche")
        //})
        Log.d("affichage","mainActivity, oncreate : distribution des cartes")

    }

}