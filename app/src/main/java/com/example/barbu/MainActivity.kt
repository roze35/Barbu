package com.example.barbu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.barbu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingMain : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        //Log.d("affichage","onCreate: MainActivity")
        bindingMain= DataBindingUtil.setContentView(this,R.layout.activity_main)
        //bindingMain.btPlay.setOnClickListener({
        //    Log.d("affichage","youpee ca marche")
        //})
        //Log.d("affichage","mainActivity, oncreate : distribution des cartes")

        /* Log.d("affichage","mainActivity, onCreate: ajout du fragment")
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        val containerId=R.id.fragmentContainerView
        //Log.d("affichage","mainactivity, oncreate : id du container de fragment "+containerId)
        fragmentTransaction.add(containerId,igFragmentSouth)
        fragmentTransaction.commit()
        //Log.d("affichage","onCreate: fin de creation de l'IG")
         */
    }

}