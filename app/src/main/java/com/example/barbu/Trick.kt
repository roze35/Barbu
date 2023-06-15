package com.example.barbu

import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils


class Trick : ObservableTrick {
    var cards: MutableSet<Card> = mutableSetOf()
    private lateinit var firstCard: Card
    private lateinit var winCard: Card
    private lateinit var winPlayer: Player

    private val observers: MutableList<ObserverTrick> = mutableListOf()

    override fun addObserver(observer: ObserverTrick) {
        observers.add(observer)
        Log.d("trace","il y a ${observers.size} observeurs")
        for (o in observers) {
            print("obs $o")
        }
    }

    override fun removeObserver(observer: ObserverTrick) {
        observers.remove(observer)
    }

    override fun notifyFollowingPlayer() {
        observers.forEach { it.followingPlayer() }
    }

    override fun notifyEndTrick() {
        observers.forEach { it.endTrick(winPlayer,cards)}
    }

    override fun notifyNewTrick() {
        observers.forEach { it.newTrick()}
    }

    fun reInit() {
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
        //Log.d("Trace", "Joueur courant " + p.pos)

        if (isEmpty()) {
            //Log.d("Trace", "Premiere carte jouer $c")
            firstCard = c
            winCard = c
            winPlayer = p
            cards.add(c)
            Referee.currentPosition=Utils.positionSuivante(p.pos)
            notifyFollowingPlayer()
        } else {
            if ((c.suit == firstCard.suit) && (c.rank > winCard.rank)) {
                //Log.d("Trace", "La carte jouee est maitre ")
                winCard = c
                winPlayer = p
            }
            cards.add(c)

            if (isOver()) {
                Log.d("Trace", "C'est la derniere carte du pli ")
                Referee.nextPosition=winPlayer.pos
                notifyEndTrick()
                winPlayer.win(cards)
                reInit()
                Referee.currentPosition=Referee.nextPosition
                notifyNewTrick()
            }else{
                //Log.d("Trace", "Ce n'est pas la derniere carte du pli ")
                Referee.currentPosition=Utils.positionSuivante(p.pos)
                notifyFollowingPlayer()
            }
        }
    }
}