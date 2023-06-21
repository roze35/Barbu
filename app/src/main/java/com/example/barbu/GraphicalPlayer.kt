package com.example.barbu

import com.example.barbu.fragment.IGFragment


class GraphicalPlayer(name:String):Player(name),ObserverTrick{
    lateinit var igFragment:IGFragment
    init {
        Referee.trick.addObserverTrick(this)
    }
    fun play(){

    }

    override fun gameOver(southScore:Int,westScore:Int,northScore:Int,eastScore:Int){
        super.gameOver(southScore,westScore,northScore,eastScore)
        igFragment.gameOver(southScore,westScore,northScore,eastScore)
    }

    override fun updateTrick() {
        igFragment.updateTrick()
    }



}