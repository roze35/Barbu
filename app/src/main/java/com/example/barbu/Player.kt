package com.example.barbu

import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Utils

open class Player(val name: String?, val pos:Position) {
    var hand: MutableSet<Card> = mutableSetOf()
    var winCards: MutableSet<Card> = mutableSetOf()

    fun initGame(){
        hand.clear()
        winCards.clear()
    }

    fun removeCard(c:Card){
        hand.remove(c)
    }

    fun addCard(card: Card?) {
        if (card != null) {
            hand.add(card)
        }
    }

    fun randomPlay(possibleCards:MutableSet<Card>, trick:Trick): Card {
        val card= Utils.randomDeal(possibleCards,trick)
        hand.remove(card)
        return card
    }

    fun win(cards:MutableSet<Card>){
        for (c in cards){
            winCards.add(c)
        }
    }

    fun showHandInLog(tag:String) {
        Log.d(tag,"")
        Log.d(tag,("$name's hand:"))
        for (card in hand) {
            Log.d(tag,("${card.rank} of ${card.suit}"))
        }
    }

}


