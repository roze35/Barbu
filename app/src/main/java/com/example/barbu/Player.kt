package com.example.barbu

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.example.barbu.cardGame.Card
import kotlin.random.Random

open class Player(val name: String?, val pos:Position) {
    var hand: MutableSet<Card> = mutableSetOf()
    var winCards: MutableSet<Card> = mutableSetOf()

    fun initGame(){
        hand.clear()
        winCards.clear()
    }

    open fun useInterface():Boolean{
        return false
    }

    fun removeCard(c:Card){
        hand.remove(c)
    }

    fun addToHand(card: Card?) {
        if (card != null) {
            hand.add(card)
        }
    }

    fun removeFromHand(card: Card) {
        hand.remove(card)
    }

    fun playOneCard(possibleCards:MutableSet<Card>, trick:Trick): Card {
        val random= Random
        val list=possibleCards.toList()
        val randomIndex=random.nextInt(possibleCards.size)
        val card=list[randomIndex]
        hand.remove(card)
        return card
    }

    fun win(cards:MutableSet<Card>){
        for (c in cards){
            winCards.add(c)
        }
    }

    fun showHand() {
        Log.d("affichage","")
        Log.d("affichage",("$name's hand:"))
        for (card in hand) {
            Log.d("affichage",("${card.rank} of ${card.suit}"))
        }

    }

    fun gameOver():MutableSet<Card>{
        return winCards
    }


}


