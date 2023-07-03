package com.example.barbu

import com.example.barbu.fragment.IGFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GraphicalPlayer(name:String,position:Position):Player(name,position){
    lateinit var igFragment:IGFragment

    override suspend fun gameOver(southScore:Int, westScore:Int, northScore:Int, eastScore:Int){
        super.gameOver(southScore,westScore,northScore,eastScore)
        withContext(Dispatchers.Main) {
            igFragment.gameOver(southScore, westScore, northScore, eastScore)
        }
    }

    fun showCard(){
            igFragment.showCards()
    }
}