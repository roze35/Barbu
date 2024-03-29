package com.example.barbu

import android.util.Log
import com.example.barbu.player.Player
import com.example.barbu.cardGame.Card
import com.example.barbu.cardGame.Deck
import com.example.barbu.utils.Position
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils

open class Referee {

    companion object {
        private var numTrick=1
        private val players=mutableListOf<Player>()
        private val deck: Deck = Deck()
        val trick: Trick = Trick()

        private var currentPosition = 0

        fun currentPlayer(): Player {
            return players[currentPosition]
        }

        fun addPlayers(){
            players.add(Player("South", Position.SOUTH))
            players.add(Player("West", Position.WEST))
            players.add(Player("North", Position.NORTH))
            players.add(Player("East", Position.EAST))
        }
        fun addPlayer(name:String){
            val p =when(players.size){
                0 -> Player(name, Position.SOUTH)
                1-> Player(name, Position.WEST)
                2-> Player(name, Position.NORTH)
                else-> Player(name, Position.EAST)
            }
            players.add(p)
        }

        fun nextPlayer(){
            currentPosition=Utils.nextPosition(currentPosition)
        }

       fun playIACards() {
            var currentPlayer = players[currentPosition]
            Log.d("affichage","traitement du pli $numTrick")
            while (!trick.isOver()) {
                val card = currentPlayer.playRandomCard(possibleCards(currentPlayer.hand))
                Log.d("affichage", "carte jouee $card par ${currentPlayer.name}")
                trick.playCard(card, currentPlayer)
                currentPosition = Utils.nextPosition(currentPosition)
                currentPlayer = players[currentPosition]
            }
            endTrick()
            numTrick += 1
            Log.d("affichage", "numero de pli $numTrick")
            currentPosition = players.indexOf(trick.winPlayer) % 4

            trick.pickup()

            if (numTrick > 8) {
                val southScore = computePoint(players[0].winCards)
                val westScore = computePoint(players[1].winCards)
                val northScore = computePoint(players[2].winCards)
                val eastScore = computePoint(players[3].winCards)
                for (p in players) p.endGame(southScore,westScore,northScore,eastScore)
                //reInit()
                //dealCards()
            }


        }

        private fun endTrick(){
            for (p in players) p.endTrick()
        }

        private fun extractRequiredSuit(hand: MutableSet<Card>): MutableSet<Card> {
            val possibleCards: MutableSet<Card> = mutableSetOf()
            for (c in hand) {
                if (c.suit == trick.requiredSuit()) possibleCards.add(c)
            }
            return possibleCards
        }

        private fun computePoint(winCards: MutableSet<Card>): Int {
            var res = 0
            for (c in winCards) {
                if (c.suit == Suit.HEARTS) res -= 5
            }
            if (res == -40) res = 40
            return res
        }

        fun showCards():String{
            var res=""
            for (p in players){
                res+="${p.position} ${p.hand.toString()}\n"
            }
            return res
        }

        fun possibleCards(hand: MutableSet<Card>): MutableSet<Card> {
            var possibleCards: MutableSet<Card>
            if (trick.isEmpty()) {
                possibleCards = hand
            } else {
                possibleCards = extractRequiredSuit(hand)
                if (possibleCards.isEmpty()) possibleCards=hand

            }
            return possibleCards
        }

        fun reInit() {
            numTrick=1
            for (p in players){
                p.reInit()
            }
            deck.reinit()
        }



        fun dealCards(){
            reInit()
            deck.shuffle()
            var card : Card?
            do {
                for (p in players) {
                    card = deck.deal()
                    p.addCard(card)
                }
            } while(!deck.isEmpty())
        }

        fun generateCsv():String{
            var res=""
            for (p in players) {res+=p.generateCsv()}
            return res
        }
    }





}