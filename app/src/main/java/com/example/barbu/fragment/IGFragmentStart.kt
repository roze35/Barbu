package com.example.barbu.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.barbu.R
import com.example.barbu.Referee
import com.example.barbu.Referee.Companion.playIACards
import com.example.barbu.RefereeHuman
import com.example.barbu.databinding.StartIgBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class IGFragmentStart: Fragment() {
    private lateinit var bindingStart: StartIgBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingStart= DataBindingUtil.inflate(inflater,R.layout.start_ig,container,false)
        bindingStart.btStart.setOnClickListener {
            Log.d("affichage","Melange des cartes")
            RefereeHuman.addPlayers()
            RefereeHuman.dealCards()
            Log.d("affichage","transition vers l'IG du jeu")
            val action = IGFragmentStartDirections.actionIGFragmentStartToIGFragment(RefereeHuman.humanPlayer)
            findNavController().navigate(action)
        }
        bindingStart.btRandom.setOnClickListener {
            val nbGame = 2
            Referee.addPlayers()
            Referee.dealCards()
            for(game in 1..nbGame) {
                for (i in 1..8) {
                    playIACards()
                }
                val context=this.context
                val fileName="dataNN.txt"
                val data=Referee.generateCsv()
                if (context != null) {
                    writeToExternalStorage(context,fileName,data)
                }

                Referee.reInit()
                Referee.dealCards()

            }
        }
        return(bindingStart.root)
    }

    fun writeToExternalStorage(context: Context, fileName: String, data: String) {
        val state = context.getExternalFilesDir(null)

        if (state != null) {
            val file = File(state, fileName)

            try {
                Log.d("affichage","youpee le stockage se passe bien")
                Log.d("affichage",fileName)
                val fileOutputStream = FileOutputStream(file)
                fileOutputStream.write(data.toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            Log.d("affichage","probleme le stockage externes est non accessible")
            // Le stockage externe n'est pas disponible ou n'est pas monté en écriture, gestion de l'erreur ici
        }
        Log.d("affichage","tout est ok")
    }
}