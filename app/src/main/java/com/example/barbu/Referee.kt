package com.example.barbu

import android.os.Bundle
import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.cardGame.Deck
import com.example.barbu.utils.Suit

class Referee {



    companion object {
        val deck: Deck = Deck()
        val trick: Trick = Trick()
        val southPlayer = Player("South", Position.SOUTH)
        val westPlayer = Player("West", Position.WEST)
        val northPlayer = Player("North", Position.NORTH)
        val eastPlayer = Player("East", Position.EAST)
        val currentPosition = Position.SOUTH

        fun requiredSuit(hand: MutableSet<Card>): MutableSet<Card> {
            var possibleCards: MutableSet<Card> = mutableSetOf()
            for (c in hand) {
                if (c.suit == trick.requiredSuit()) possibleCards.add(c)
            }
            return possibleCards
        }

        fun computePoint(winCards: MutableSet<Card>): Int {
            var res = 0
            for (c in winCards) {
                if (c.suit == Suit.HEARTS) res -= 5
            }
            if (res == -40) res = 40
            return res
        }

        fun possibleCards(hand: MutableSet<Card>): MutableSet<Card> {
            var possibleCards: MutableSet<Card> = mutableSetOf()
            if (trick.isEmpty()) {
                possibleCards = hand
            } else {
                possibleCards = requiredSuit(hand)
                if (possibleCards.isEmpty()) possibleCards=hand

            }
            return possibleCards
        }

        fun reinit() {
            southPlayer.initGame()
            westPlayer.initGame()
            northPlayer.initGame()
            eastPlayer.initGame()
            deck.reinit()
        }

        fun playCard(p: Player, c: Card): Boolean {
            var possibleCards: MutableSet<Card>
            possibleCards = possibleCards(p.hand)
            if (c in possibleCards) {
                trick.playCard(c)
                p.removeCard(c)
                return true
            } else return false
        }

        /*fun joueurSuivant() {
            when(currentPosition){
                Position.SOUTH->{
                    if()
                    val action =
                }
            }

        }*/

        fun playCard(p: Position, c: Card): Boolean {
            when (p) {
                Position.SOUTH -> {return playCard(southPlayer, c) }
                Position.WEST -> {return playCard(westPlayer, c) }
                Position.NORTH -> {return playCard(northPlayer, c) }
                Position.EAST -> {return playCard(eastPlayer, c) }
            }
           // joueurSuivant()
            return false
        }

        fun dealCards(){
            reinit()
            deck.shuffle()
            var card : Card?
            do {
                card = deck.deal()
                westPlayer.addToHand(card)
                card = deck.deal()
                northPlayer.addToHand(card)
                card = deck.deal()
                eastPlayer.addToHand(card)
                card = deck.deal()
                southPlayer.addToHand(card)
            } while(!deck.isEmpty())
            southPlayer.showHand()
        }


    }






}