package com.example.barbu.utils

import com.example.barbu.Position
import com.example.barbu.Trick
import com.example.barbu.cardGame.Card
import kotlin.random.Random

class Utils {
    companion object {
        fun positionSuivante(position:Position): Position {
            when (position) {
                Position.SOUTH -> return Position.WEST
                Position.WEST -> return Position.NORTH
                Position.NORTH -> return Position.EAST
                Position.EAST -> return Position.SOUTH
            }
        }

        fun randomDeal(possibleCards:MutableSet<Card>, trick: Trick): Card {
            val random= Random
            val list=possibleCards.toList()
            val randomIndex=random.nextInt(possibleCards.size)
            val card=list[randomIndex]
            return card
        }
    }
}