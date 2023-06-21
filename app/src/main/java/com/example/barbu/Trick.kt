package com.example.barbu

import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Suit


class Trick : ObservableTrick  {
    var cards: MutableSet<Card> = mutableSetOf()
    private lateinit var firstCard: Card
    private lateinit var winCard: Card
    lateinit var winPlayer: Player

    private val observers: MutableList<ObserverTrick> = mutableListOf()

    fun size():Int{
        return cards.size
    }
    private fun reInit() {
        cards.clear()
    }

    fun isEmpty(): Boolean {
        return cards.size == 0
    }

    fun isOver(): Boolean {
        return cards.size == 4
    }

    fun requiredSuit(): Suit {
        return firstCard.suit
    }

    fun playCard(c: Card, p: Player) {
        if (isEmpty()) {
            //Log.d("Trace", "Premiere carte jouer $c")
            firstCard = c
            winCard = c
            winPlayer = p
            cards.add(c)
        } else {
            if ((c.suit == firstCard.suit) && (c.rank > winCard.rank)) {
                //Log.d("Trace", "La carte jouee est maitre ")
                winCard = c
                winPlayer = p
            }
            cards.add(c)
        }
        notifyObserverTrick()
    }

    fun pickup(){
        Log.d("affichage","Le gagnant est ${winPlayer.name}")
        winPlayer.winCards(cards)
        reInit()
    }

    override fun addObserverTrick(o: ObserverTrick) {
        observers.add(o)
    }

    override fun removeObseverTrick(o: ObserverTrick) {
        observers.remove(o)
    }

    override fun notifyObserverTrick() {
        for (observer in observers) {
            observer.updateTrick()
        }
    }
}