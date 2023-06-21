package com.example.barbu

import android.util.Log
import com.example.barbu.cardGame.Card
import com.example.barbu.cardGame.Deck
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils

class Referee {

    companion object {
        private var nbTrick=1
        private val players=mutableListOf<Player>()
        private val deck: Deck = Deck()
        val trick: Trick = Trick()

        private var currentPosition = 0
        lateinit var humanPlayer:GraphicalPlayer

        fun addPlayer(p:Player){
            players.add(p)
            if (p is GraphicalPlayer) humanPlayer=p
        }
        private fun justWait(){
            try {
                Thread.sleep(100)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        fun nextPlayer(){
            if (trick.isOver()) {
                justWait()
                nbTrick+=1
               Log.d("affichage", "numero de pli $nbTrick")
                currentPosition=(players.indexOf(trick.winPlayer)+3)%4

                // astuce d'informaticien : comme avec nextJoueur on va passer au joueurSvuiant
                //on se place juste avant le joueur devant commencer le tour suivant

                trick.pickup()
                humanPlayer.updateTrick()
                if (nbTrick>8){
                    val southScore= computePoint(players[0].winCards)
                    val westScore= computePoint(players[1].winCards)
                    val northScore= computePoint(players[2].winCards)
                    val eastScore= computePoint(players[3].winCards)
                    gameOver(southScore,westScore,northScore,eastScore)
                }else{
                    nextPlayer()
                }
            } else {
                currentPosition=Utils.positionSuivante(currentPosition)
                val currentPlayer= players[currentPosition]

                if (currentPlayer is GraphicalPlayer){
                    currentPlayer.play()
                    justWait()
                }else {
                    val card = currentPlayer.playRandomCard(possibleCards(currentPlayer.hand))
                    Log.d("affichage","carte jouee $card par ${currentPlayer.name}")
                    trick.playCard(card,currentPlayer)
                    justWait()
                    nextPlayer()
                }
            }
        }
        fun playCard(c: Card): Boolean {
            val p= players[currentPosition]
            val possibleCards: MutableSet<Card> = possibleCards(p.hand)
            return if (c in possibleCards) {
                p.removeCard(c)
                trick.playCard(c,p)
                true
            } else false
        }

        private fun gameOver(southScore:Int,westScore:Int,northScore:Int,eastScore:Int){
            for (p in players){
                p.gameOver(southScore,westScore,northScore,eastScore)
            }

        }

        private fun requiredSuit(hand: MutableSet<Card>): MutableSet<Card> {
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


        private fun possibleCards(hand: MutableSet<Card>): MutableSet<Card> {
            var possibleCards: MutableSet<Card>
            if (trick.isEmpty()) {
                possibleCards = hand
            } else {
                possibleCards = requiredSuit(hand)
                if (possibleCards.isEmpty()) possibleCards=hand

            }
            return possibleCards
        }

        private fun reInit() {
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


    }



}