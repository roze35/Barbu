package com.example.barbu

import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.cardGame.Deck
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils

class Referee : ObserverTrick{



    companion object {
        val deck: Deck = Deck()
        val trick: Trick = Trick()
        val southPlayer = GraphicalPlayer("South", Position.SOUTH)
        val westPlayer = Player("West", Position.WEST)
        val northPlayer = Player("North", Position.NORTH)
        val eastPlayer = Player("East", Position.EAST)
        var currentPosition = Position.SOUTH
            set(value) {
                //Log.d("Trick","Modification de la position courante "+value)
                field=value
            }
        var nextPosition=Position.WEST

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


        fun playRandom():Card{
            val currentPlayer:Player=when(currentPosition){
                Position.SOUTH->{southPlayer}
                Position.WEST->{westPlayer}
                Position.NORTH->{northPlayer}
                Position.EAST->{eastPlayer}
            }
                val possibleCards= possibleCards(currentPlayer.hand)
                val c= currentPlayer.randomPlay(possibleCards, trick)
                trick.playCard(c,currentPlayer)
                currentPlayer.removeCard(c)
                return c

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
            //Log.d("affichage","apple de playcard pour le joueur "+p.name)
            //Log.d("affichage","test la carte "+c)
            //Log.d("affichage","Liste des cartes du joueurs : ")
            //p.showHandInLog("affichage")
            var possibleCards: MutableSet<Card> = possibleCards(p.hand)
            //Log.d("affichage","Nombre de cartes possibles : "+possibleCards.size)
            //Log.d("affichage","Les cartes possibles : ")
            //Log.d("affichage",""+possibleCards)
            if (c in possibleCards) {
                trick.playCard(c,p)
                p.removeCard(c)
                return true
            } else return false
        }



        fun playCard(c: Card): Boolean {
            when (currentPosition) {
                Position.SOUTH -> {return playCard(southPlayer, c) }
                Position.WEST -> {return playCard(westPlayer, c) }
                Position.NORTH -> {return playCard(northPlayer, c) }
                Position.EAST -> {return playCard(eastPlayer, c) }
            }
            // non attention le joueur suivant doit etre fait dans trick
            //currentPosition= Utils.positionSuivante(currentPosition)

            return false
        }

        fun dealCards(){
            reinit()
            deck.shuffle()
            var card : Card?
            do {
                card = deck.deal()
                westPlayer.addCard(card)
                card = deck.deal()
                northPlayer.addCard(card)
                card = deck.deal()
                eastPlayer.addCard(card)
                card = deck.deal()
                southPlayer.addCard(card)
            } while(!deck.isEmpty())
            //southPlayer.showHand()
        }


    }

    override fun followingPlayer() {
    }

    override fun endTrick(winPlayer:Player,trick:MutableSet<Card>) {
        winPlayer.win(trick)
    }

    override fun newTrick(){

    }


}