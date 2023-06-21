package com.example.barbu.utils

import com.example.barbu.cardGame.Card
import kotlin.random.Random

class Utils {
    companion object {
        fun positionSuivante(position:Int): Int {
            return (position+1)%4
        }

        fun randomDeal(possibleCards:MutableSet<Card>): Card {
            val random= Random
            val list=possibleCards.toList()
            val randomIndex=random.nextInt(possibleCards.size)
            val card=list[randomIndex]
            return card
        }
    }
}