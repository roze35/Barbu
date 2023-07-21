package com.example.barbu.cardGame

import com.example.barbu.utils.Rank
import com.example.barbu.utils.Suit

class Deck {
    val cards: MutableList<Card> = mutableListOf()

    init {
        reinit()
    }
    fun reinit(){
        cards.clear()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
    }
    fun isEmpty():Boolean{
        return cards.isEmpty()
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun deal(): Card? {
        if (cards.isEmpty()) {
            return null
        }
        return cards.removeAt(0)
    }
}

fun main() {
    val deck = Deck()
    deck.shuffle()

    for (i in 1..5) {
        val card = deck.deal()
        if (card != null) {
            println("Dealt card: ${card.rank} of ${card.suit}")
        } else {
            println("No more cards in the deck.")
        }
    }
}