package com.example.barbu

import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Suit


class Trick  {
    var southCard: Card?=null
    var westCard: Card?=null
    var northCard: Card?=null
    var eastCard: Card?=null
    private var nbCard=0

    private var firstCard: Card?=null
    private var firstPlayer: Player?=null
    private var winCard: Card?=null
    var winPlayer: Player?=null

    private fun reInit() {
        nbCard=0
        southCard=null
        westCard=null
        northCard=null
        eastCard=null
    }

    fun isEmpty(): Boolean {
        return nbCard == 0
    }

    fun isOver(): Boolean {
        return nbCard == 4
    }

    fun requiredSuit(): Suit {
        return firstCard!!.suit
    }
    private fun addCard(c:Card, pos:Position){
        when(pos){
            Position.SOUTH->southCard=c
            Position.EAST->eastCard=c
            Position.NORTH->northCard=c
            Position.WEST->westCard=c
        }
        nbCard++
    }

    fun playCard(c: Card, p: Player) {
        if (isEmpty()) {
            //Log.d("Trace", "Premiere carte jouer $c")
            firstCard = c
            firstPlayer=p
            winCard = c
            winPlayer = p
            addCard(c,p.position)
        } else {
            if ((c.suit == firstCard!!.suit) && (c.rank > winCard!!.rank)) {
                //Log.d("Trace", "La carte jouee est maitre ")
                winCard = c
                winPlayer = p
            }
            addCard(c,p.position)
        }
    }

    fun pickup(){
        Log.d("affichage","Le gagnant est ${winPlayer!!.name}")
        winPlayer!!.winCards.add(southCard!!)
        winPlayer!!.winCards.add(westCard!!)
        winPlayer!!.winCards.add(northCard!!)
        winPlayer!!.winCards.add(eastCard!!)
        reInit()
    }

}