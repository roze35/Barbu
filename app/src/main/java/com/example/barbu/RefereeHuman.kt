package com.example.barbu

import android.util.Log
import com.example.barbu.player.GraphicalPlayer
import com.example.barbu.player.Player
import com.example.barbu.cardGame.Card
import com.example.barbu.cardGame.Deck
import com.example.barbu.utils.Position
import com.example.barbu.utils.Suit
import com.example.barbu.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RefereeHuman {

    companion object {
        private var numTrick=1
        private val players=mutableListOf<Player>()
        private val deck: Deck = Deck()
        val trick: Trick = Trick()

        private var currentPosition = 0
        lateinit var humanPlayer: GraphicalPlayer
        private var delay: Long =1000

        fun currentPlayer(): Player {
            return players[currentPosition]
        }

        suspend fun justWait(){
            delay(delay)
        }

        fun addPlayers(){
            humanPlayer= GraphicalPlayer("South", Position.SOUTH)
            players.add(humanPlayer)
            players.add(Player("West", Position.WEST))
            players.add(Player("North", Position.NORTH))
            players.add(Player("East", Position.EAST))
        }
        fun addPlayer(name:String){
            val p =when(players.size){
                0 -> GraphicalPlayer(name, Position.SOUTH)
                1-> Player(name, Position.WEST)
                2-> Player(name, Position.NORTH)
                else-> Player(name, Position.EAST)
            }
            players.add(p)
            if (p is GraphicalPlayer) humanPlayer=p
        }

        fun nextPlayer(){
            currentPosition=Utils.nextPosition(currentPosition)
        }

        suspend fun playIACards()= withContext(Dispatchers.Default) {
            var currentPlayer = players[currentPosition]
            while ((!trick.isOver()) && (currentPlayer !is GraphicalPlayer)) {
                val card = currentPlayer.playRandomCard(possibleCards(currentPlayer.hand))
                Log.d("affichage", "carte jouee $card par ${currentPlayer.name}")
                trick.playCard(card, currentPlayer)
                withContext(Dispatchers.Main) {
                    humanPlayer.showCard()
                }
                delay(delay)
                currentPosition = Utils.nextPosition(currentPosition)
                currentPlayer = players[currentPosition]
            }

            if (trick.isOver()) {
                numTrick += 1
                Log.d("affichage", "numero de pli $numTrick")
                currentPosition = players.indexOf(trick.winPlayer)  % 4

                withContext(Dispatchers.Main) {
                    humanPlayer.showCard()
                }

                trick.pickup()

                withContext(Dispatchers.Main) {
                    humanPlayer.showCard()
                }
                if (numTrick > 8) {
                    val southScore = computePoint(players[0].winCards)
                    val westScore = computePoint(players[1].winCards)
                    val northScore = computePoint(players[2].winCards)
                    val eastScore = computePoint(players[3].winCards)
                    gameOver(southScore, westScore, northScore, eastScore)
                } else {
                    withContext(Dispatchers.Main) {
                        humanPlayer.igFragment.trickOver()
                    }
                }
            }
        }


        fun playCard(c: Card): Boolean {
            val p= players[currentPosition]
            val possibleCards: MutableSet<Card> = possibleCards(p.hand)
            return if (c in possibleCards) {
                p.hand.remove(c)
                trick.playCard(c,p)
                true
            } else false
        }

        private suspend fun gameOver(southScore:Int, westScore:Int, northScore:Int, eastScore:Int){
            for (p in players){
                p.gameOver(southScore,westScore,northScore,eastScore)
            }

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

        private fun reInit() {
            numTrick=1
            for (p in players){
                p.reInit()
            }
            deck.reinit()
        }



        fun dealCards(){
            Log.d("affichage","debut de dealCards")
            Log.d("affichage","nombre de joueurs ${players.size}")
            reInit()
            deck.shuffle()
            var card : Card?
            do {
                Log.d("affichage","debut d'un pli")
                Log.d("affichage","nombre de cartes restantes ${deck.cards.size}")
                for (p in players) {
                    card = deck.deal()
                    p.addCard(card)
                }
            } while(!deck.isEmpty())
            Log.d("affichage","debut de dealCards")
        }
    }



}