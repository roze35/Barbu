package com.example.barbu

import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Suit


class Trick {
    val cards: MutableSet<Card> = mutableSetOf()
    lateinit var firstCard:Card

    fun reinit(){
        cards.clear()
    }

    fun isEmpty():Boolean{
        return cards.isEmpty()
    }

    fun requiredSuit(): Suit?{
        return firstCard.suit
    }

    fun playCard(c: Card){
        if (isEmpty()) firstCard=c
        cards.add(c)
    }

    fun winCards():MutableSet<Card>{
        return cards;
    }
}